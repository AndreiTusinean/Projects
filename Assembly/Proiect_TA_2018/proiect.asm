.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem msvcrt.lib, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern exit: proc
extern printf: proc
extern scanf: proc
extern fscanf: proc
extern fprintf: proc
extern fopen: proc
extern fclose: proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data

;Pentru expresie
ER			DB "EROARE : Operatia introdusa este invalida", 0
matrice_a	DB "a= ",0
matrice_b	DB "b= ",0
Operatie	DB "Dati o expresie:",0
sursa		DB 0
expresie 	DB 100 DUP(0)
m_A			DD 100 DUP(0)
m_B			DD 100 DUP(0)
m_R			DD 100 DUP(0)
Lungime_A 	DD 0
Lungime_B 	DD 0
Linii_A		DD 0
Linii_B		DD 0
Scalar_a	DD 0
Inm			DD 0	


;Pentru format
format_s				DB	"%s",0
format_c				DB	"%c",0
format_d				DB	"%d",0

;Pentru fisier
fisier_a		DB 100 DUP(0);DB " ",0
fisier_b		DB 100 DUP(0);DB " ",0
fisier_rezultat DB "rezultat.txt",0
locatie_rez		DB "Rezultat: rezultat.txt",0
mode_read		DB "r",0
mode_write		DB "w",0
f				DD	0
aux				DD  0
newline1		DB " " , 10
spatiu			DB " " , 32

.code

;MACROURI

newline_f macro 
push offset newline1
push offset format_s
push f
call fprintf
add esp , 12
endm

spatiu_f macro
push offset spatiu
push offset format_s
push f
call fprintf
add esp,12
endm

citire_numar macro numar
push offset numar
push offset format_d
call scanf
add ESP,8
endm

afiseaza_mesaj	macro	mesaj 
push offset mesaj
push offset format_s
call printf
add esp , 8
endm

citire_sursa macro sursa
push offset sursa
push offset format_s
call scanf
add esp ,8
endm

deschide_fisier macro mode , nume
	push 	offset mode
	push 	offset nume
	call 	fopen
	add 	ESP , 8
	mov 	f , EAX	
endm

inchide_fisier macro pointer
	push 	pointer
	call 	fclose
	add 	ESP , 4
endm

citire_din_fisier macro pointer_fisier , format , locatie
	push offset locatie
	push offset format
	push pointer_fisier
	call fscanf
	add ESP , 12
endm

adunare_m macro lungime1
Adunare:
		xor eax , eax									
		add eax , m_A[ebx]
		add eax , m_B[ebx]
		mov m_R[ebx] , eax
		add ebx , 4
		cmp ebx , lungime1
		jl Adunare
		jmp iesire
endm

scadere_m macro lungime2
Scadere:
		xor eax , eax									
		mov eax , m_A[ebx]
		sub eax , m_B[ebx]
		mov m_R[ebx] , eax
		add ebx , 4
		cmp ebx , lungime2
		jl Scadere
		jmp iesire
endm

scalar_m macro lungime3
Scalar:
		xor eax , eax									
		mov eax , m_A[ebx]
		mov ecx,Scalar_a
		mul ecx
		mov m_R[ebx] , eax
		add ebx , 4
		cmp ebx , lungime3
		jl Scalar
		jmp iesire
endm

inmultire_m macro lungime4
;ebx pentru linii
;esi pentru coloane
	Linii:
	
	add ebx , Linii_A
	;cmp ebx , Lungime_A
	jmp Inmultire
	
	Coloane:
	
	add esi , 4
	cmp esi,lungime4
	jl Inmultire
	xor esi,esi
	jmp Inmultire
	
	Inmultire:
		xor eax , eax									
		mov eax , m_A[ebx]
		mov ecx,  m_B[esi]
		mul ecx
		mov m_R[ebx] , eax
		add ebx , Linii_A
		add esi , 4
		cmp ebx , lungime4
		jl Inmultire
		cmp ebx , lungime4
		je Linii
		cmp esi, Linii_A
		je Coloane
		jmp iesire
endm

Calculare_lungime_matrice macro lungime5 , linii
xor eax , eax
	xor ecx , ecx
	mov ecx , 4
	xor eax , eax
	mov eax , lungime5
	mul ecx
	mov linii,eax										;linii = 4*numarul de linii				
	
	xor ecx , ecx										;lungime5 = lungime5*lungime5*4   pentru a citi n**2 elemente
	mov ecx , 4
	xor eax , eax
	mov eax , lungime5
	mul ecx
	mul lungime5
	mov lungime5 , eax
endm

start:

	afiseaza_mesaj			Operatie					;Citire de 
	citire_sursa			expresie					;operatie
	
	afiseaza_mesaj  		matrice_a					;Citesc sursa
	citire_sursa			fisier_a					;pentru matrice_a
	
	deschide_fisier 		mode_read , fisier_a		;deschid fisierul in mod citire(read)
	citire_din_fisier		f , format_d , Lungime_A	;Citire marime matrice_a
	
	
	Calculare_lungime_matrice Lungime_A , Linii_A		;Pentru parcurgere matrice A
	
	xor ebx , ebx
	Matricea_A:
		citire_din_fisier	f , format_d , aux			;Citire elemente
		xor eax , eax									;Populare sir
		mov eax , aux
		mov m_A[ebx] , eax
		add ebx , 4
		cmp ebx , Lungime_A
		jl Matricea_A
	
	inchide_fisier				 f
	
	;Matricea B
	afiseaza_mesaj  		matrice_b					;Citire sursa fisier
	citire_sursa			fisier_b					;pentru m_B
	
	
	deschide_fisier 		mode_read , fisier_b		;deschid fisierul in mod citire(read)
	citire_din_fisier		f , format_d , Lungime_B	;Citire marime m_B
	
	Calculare_lungime_matrice	Lungime_B , Linii_B		;Pentru parcurgere matrice B
	
	xor ebx , ebx
	Matricea_B:
		citire_din_fisier	f , format_d , aux			;Citire elemente
		xor eax , eax									;Populare m_B
		mov eax , aux
		mov m_B[ebx] , eax
		add ebx , 4
		cmp ebx , Lungime_B
		jl Matricea_B
	
	inchide_fisier				f
	
	;Verificare operatie
		
		xor ebx,ebx
		xor esi,esi
		cmp 	expresie[1] , '+'	;Operatia este adunare
		je		Adunare
		
		cmp		expresie[1] , '-'	;Operatia este scadere
		je 		Scadere
		
		mov al,expresie[0]       	;Scalar_a primeste valoare numarului citit . Ex : operatie:4A , Scalar_a devine 4 
		mov Scalar_a,eax
		sub Scalar_a,'0'
		
		cmp		expresie[1] , '*'	;Operatia este inmultire
		je 		Inmultire
		
		xor ebx,ebx
		cmp 	expresie[0] , '0'	;Verificam daca scalarul dat este un numar si se face saltul la inmultire cu scalar
		jl 		eroare
		cmp 	expresie[0] , '9'
		jg 		eroare
		jmp 	Scalar
		
		jmp eroare
		
	;Adunarea A+B
	adunare_m Lungime_A
		
	;Scaderea A-B
	scadere_m Lungime_A
	
	;Inmultire cu scalar aA
	scalar_m  Lungime_A
	
	;Inmultire A*B
	inmultire_m Lungime_A
		
	eroare:						;Daca operatia data nu este corecta 
	afiseaza_mesaj 		ER		;se va afisa o eroare si programul se va termina
	push 0
	call exit
		
	iesire:
	deschide_fisier				mode_write , fisier_rezultat	;Afisare rezultatului in rezultat.txt
	
	xor ebx , ebx
	xor esi , esi
	
	N_line_B:
		newline_f
		mov esi , 0
		cmp ebx, Lungime_B
		je Gata_afisare_B
	Afisare_M_B:					;Afisare M_R ( matrice rezultat)
		spatiu_f
		push   m_R[ebx]
		push offset format_d
		push  f
		call fprintf
		add esp , 12 
		add ebx , 4
		add esi , 4
		cmp esi , Linii_A			
		je N_line_B
		cmp ebx , Lungime_B
		jl Afisare_M_B
	Gata_afisare_B:
	inchide_fisier				 f
	
	push offset newline1
	push offset format_s
	call printf
	add esp , 8
	afiseaza_mesaj		locatie_rez
	
	push 0
	call exit
end start
