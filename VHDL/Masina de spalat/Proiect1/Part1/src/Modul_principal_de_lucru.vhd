library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use ieee.std_logic_unsigned.all;
use ieee.numeric_std.all;

entity CARACTERISTICI_SPALARE is  
	port(
	SEL_REGIM		 : in STD_LOGIC;
	CLATIRE			 : inout STD_LOGIC; 
	PRESP 			 : inout STD_LOGIC;	
	CLK				 : in STD_LOGIC;  
	ENABLE0			 : in STD_LOGIC; 
	ENABLE1			 : in STD_LOGIC;   
	USA				 : in STD_LOGIC;   
	START 			 : in STD_LOGIC;
	ANOD   			 : out STD_LOGIC_VECTOR(3 downto 0);
	CATOD			 : out STD_LOGIC_VECTOR(7 downto 0);
	CLATIRE_SELECTATA: inout STD_LOGIC;
	PRESPALARE_SELECTATA : inout STD_LOGIC;
	TEMP             : inout INTEGER range 0  to 90;
	RPM				 : inout INTEGER range 800 to 1200 
	);
end CARACTERISTICI_SPALARE;

architecture ARC_08 of CARACTERISTICI_SPALARE is

signal REGIM				 			: STD_LOGIC;
signal FINISH							: STD_LOGIC := '0';
signal APA_AL,APA_EVC					: STD_LOGIC := '0';
signal STARE_USA,READY					: STD_LOGIC := '0';
signal MOD_SPALARE 						: STD_LOGIC_VECTOR (2 downto 0):="000";
signal TEMP_M,RPM_M						: INTEGER;
signal TEMP_A							: INTEGER range 0  to 90 :=0;
signal TEMP_CURENTA						: INTEGER range 15 to 90 :=15;
signal RPM_A							: INTEGER range 800 to 1200 :=801;
signal RPM_CURENT						: INTEGER range 0 to 1200 ;
signal TIMP 							: INTEGER range 40 to 1000 ;
signal TIMP2							: STD_LOGIC_VECTOR(6 downto 0);
signal SEC,MIN,HR						: STD_LOGIC_VECTOR(6 downto 0);
signal HR_VECTOR,MIN_VECTOR				: STD_LOGIC_VECTOR(7 downto 0);
signal CLK_TIME		 					: STD_LOGIC;

component MOD_REGIM is
	port(
	S,START	:in 	STD_LOGIC;
	AM		:out 	STD_LOGIC);
end component;

component CLATIRE_SUPLIMENTARA is
	port(
	S,START		:in 	STD_LOGIC;
	YN			:out 	STD_LOGIC);
end component;

component PRESPALARE is
	port(
	S,START		:in 	STD_LOGIC;
	YN			:out 	STD_LOGIC);
end component;

component TEMPERATURA is
	port(
	CLK 				:in STD_LOGIC ;
	ENABLE,REGIM,START	:in STD_LOGIC ;
 	TEMP   				:inout INTEGER range 0 to 90 :=0);
end component;

component VITEZA is
	port(
	CLK 				:in STD_LOGIC				  ;
	ENABLE,REGIM,START	:in STD_LOGIC				  ;
	RPM					:inout INTEGER range 800 to 1200 :=801);
end component;

component SELECTARE_MOD_AUTOMAT is
	Port(
		AM,START: 			in  	STD_LOGIC;
		CLK:				in 		STD_LOGIC;
		MOD_SPALARE: 		inout 	STD_LOGIC_VECTOR (2 downto 0):="000");
end component;

component INCEPERE_SPALARE is
	port(USA,START							: in 	STD_LOGIC;		  
		REGIM_SELECTAT,CLATIRE,PRESPALARE 	: in 	STD_LOGIC;
		TEMPERATURA 						: in 	INTEGER range 0  to 90    := 0;
		VITEZA 								: in 	INTEGER range 800 to 1200 :=801;
		READY								: out 	STD_LOGIC);
end component;

component COUNTDOWN_VECTOR is
	port(
		clk     : in 	STD_LOGIC	:='0';
		ENABLE  : in	STD_LOGIC	:='0';
		TIMP2	: in 	std_logic_vector(6 downto 0);
		seconds : inout std_logic_vector(6 downto 0);
     	minutes : inout std_logic_vector(6 downto 0);
     	hours   : inout std_logic_vector(6 downto 0)
	     );
end component;

component DECOD is
	port(
	Intrare : in STD_LOGIC_VECTOR(6 downto 0);
	Iesire:out STD_LOGIC_VECTOR(7 downto 0)
	);
end component;	

component AFISOR is
port( 
	minu : in std_logic_vector(7 downto 0);
	hr: in std_logic_vector(7 downto 0);
	clk: in std_logic;
	anod: out std_logic_vector(3 downto 0);
	catod: out std_logic_vector(7 downto 0)
	);
end component AFISOR;	

component DIV is
	port(
	clk:in STD_LOGIC;
	clk1:out STD_LOGIC
	);         
end component;

begin  

	C0 : MOD_REGIM 				port map (SEL_REGIM,START,REGIM);
	C1 : CLATIRE_SUPLIMENTARA 	port map (CLATIRE,START,CLATIRE_SELECTATA);
	C2 : PRESPALARE 			port map (PRESP,START,PRESPALARE_SELECTATA);
	C22: DIV					port map (CLK,CLK_TIME);
	C3 : TEMPERATURA 			port map (CLK,ENABLE0,REGIM,START,TEMP_M);
	C4 : VITEZA 				port map (CLK,ENABLE1,REGIM,START,RPM_M);
	C5 : SELECTARE_MOD_AUTOMAT	port map (REGIM,START,CLK,MOD_SPALARE);
	C6 : INCEPERE_SPALARE 		port map (STARE_USA,START,REGIM,CLATIRE_SELECTATA,PRESPALARE_SELECTATA,TEMP,RPM,READY);
	C7 : COUNTDOWN_VECTOR		port map (CLK_TIME,READY,TIMP2,SEC,MIN,HR);
	C8 : DECOD					port map (MIN,MIN_VECTOR);
	C9 : DECOD					port map (HR,HR_VECTOR); 
	C10: AFISOR					port map (MIN_VECTOR, HR_VECTOR, CLK, ANOD, CATOD);
	
	process (REGIM,CLATIRE,CLATIRE_SELECTATA,PRESP,PRESPALARE_SELECTATA,MOD_SPALARE)
	begin
		if READY /= '1' then
		if REGIM = '0' then	--MANUAL 
			report "Modul de functionare este MANUAL" severity note;
			if CLATIRE_SELECTATA = '0' and PRESPALARE_SELECTATA = '0' then 
				TIMP <= 40;
			end if;
			if CLATIRE_SELECTATA = '0' and PRESPALARE_SELECTATA = '1'then 
				TIMP <= 50;
			end if;
			if CLATIRE_SELECTATA = '1' and PRESPALARE_SELECTATA = '0'then 
				TIMP <= 60;
			end if;
			if CLATIRE_SELECTATA = '1' and PRESPALARE_SELECTATA = '1' then 
				TIMP <= 70;
			end if;
			
		elsif REGIM = '1' then	-- AUTOMAT
			report "Modul de functionare este AUTOMAT" severity note;
			if MOD_SPALARE = "000" then		--Spalare rapida
				RPM_A	<= 1200;
				TEMP_A	<= 30;
				CLATIRE	<= '0';
				PRESP	<= '0';
				TIMP	<= 40;
			end if;
			if MOD_SPALARE = "001" then		--Camasi
				RPM_A	<= 800;
				TEMP_A	<= 60;
				CLATIRE	<= '0';
				PRESP	<= '0';
				TIMP	<= 40;
			end if;
			if MOD_SPALARE = "010" then		--Culori inchise
				RPM_A	<= 1000;
				TEMP_A	<= 40;
				CLATIRE	<= '1';
				PRESP	<= '0';
				TIMP	<= 60;
			end if;
			if MOD_SPALARE = "011" then	  	--Rufe murdare
				RPM_A	<= 1000;
				TEMP_A	<= 40;
				CLATIRE	<= '0';
				PRESP	<= '1';
				TIMP	<= 50;
			end if;
			if MOD_SPALARE = "100" then	 	--Antialergic
				RPM_A	<= 1200;
				TEMP_A	<= 90;
				CLATIRE	<= '1';
				PRESP	<= '0';
				TIMP	<= 60;
			end if;
		end if;		
		end if;
	end process; 
	
	TEMP_RPM : process(TEMP_M,RPM_M,TEMP_A,RPM_A)
	begin
		if    REGIM = '0' then
			TEMP <=	TEMP_M;
			RPM  <=	RPM_M;
		elsif REGIM = '1' then
			TEMP <= TEMP_A;
			RPM  <=	RPM_A;
		end if;
	end process TEMP_RPM;
	
	process(TIMP2,TIMP,CLATIRE_SELECTATA,PRESPALARE_SELECTATA)
	begin
		TIMP2<= conv_std_logic_vector(TIMP,7);
	end process;
	
	process(CLK,SEC,MIN,HR)
	begin
		if READY /= '1' and  FINISH = '0' then
			STARE_USA <= USA;
		end if;
		if READY = '1' then
			if TEMP_CURENTA <TEMP and SEC = 59 then
				TEMP_CURENTA <= TEMP_CURENTA + 1 after 1 sec;
			end if;
			if TEMP_CURENTA <TEMP and SEC /= 0 then
				TEMP_CURENTA <= TEMP_CURENTA + 1 after 2 sec;
			end if;
			if SEC = "0000000" and MIN = "0000000" and HR = "0000000" then
				STARE_USA 	  <= '0' after 1 min;
				FINISH <= '1';
				--READY <= '0';
			end if;
			if PRESPALARE_SELECTATA = '1'  then 		--prespalare
					APA_AL <= '1';
					RPM_CURENT <= 60;
					APA_AL <= '0' after 2 min;
					APA_EVC <= '1' after 8 min;
					APA_EVC <= '0' after 10 min;
					--Spalare principala
					APA_AL <= '1';
					APA_AL <= '0' after 12 min;
					RPM_CURENT <= 60 after 10 min;
					APA_EVC <= '1' after 28 min;
					APA_EVC <= '0' after 30 min;
					--Clatire
					APA_AL  <= '1' after 30 min;
					APA_AL  <= '0' after 32 min;
					RPM_CURENT <= 120 after 30 min;
					APA_EVC <= '1' after 38 min;
					APA_EVC <= '0' after 40 min;
					--Clatire suplimentara
					if CLATIRE_SELECTATA = '1' then
						APA_AL  <= '1' after 40 min;
						APA_AL  <= '0' after 42 min;
						RPM_CURENT <= 120 after 40 min;
						APA_EVC <= '1' after 58 min;
						APA_EVC <= '0' after 60 min;
						--centrifugare
						RPM_CURENT<= RPM after 60 min;  --70
					end if;	 
					RPM_CURENT<= RPM after 40 min; --50
				else  
					--Spalare principala
					APA_AL <= '1';
					APA_AL <= '0' after 2 min;
					RPM_CURENT <= 60;
					APA_EVC <= '1' after 18 min;
					APA_EVC <= '0' after 20 min;
					--Clatire
					APA_AL  <= '1' after 20 min;
					APA_AL  <= '0' after 22 min;
					RPM_CURENT <= 120;
					APA_EVC <= '1' after 28 min;
					APA_EVC <= '0' after 30 min;
					--Clatire suplimentara
					if CLATIRE_SELECTATA = '1' then
						APA_AL  <= '1' after 30 min;
						APA_AL  <= '0' after 32 min;
						RPM_CURENT <= 120 ;
						APA_EVC <= '1' after 48 min;
						APA_EVC <= '0' after 50 min; 
						--centrifugare
						RPM_CURENT<= RPM after 50 min;	--60
					end if;
					RPM_CURENT<= RPM after 30 min; --40
			end if;
		end if;
	end process;
	
	   --,APA_EVC
	
end ARC_08;	
	
	