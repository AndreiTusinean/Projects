library IEEE;
use ieee.std_logic_1164.all;

--Spalarea nu va incepe fara ca usa sa fie inchisa , sa fie dat start si sa fie selectat un mod de spalare
entity INCEPERE_SPALARE is
	port(USA,START							: in 	STD_LOGIC;		  
		REGIM_SELECTAT,CLATIRE,PRESPALARE 	: in 	STD_LOGIC;
		TEMPERATURA 						: in 	INTEGER range 0  to 90    := 0;
		VITEZA 								: in 	INTEGER range 800 to 1200 := 801;
		READY								: out 	STD_LOGIC);
end INCEPERE_SPALARE;

architecture ARC_03 of INCEPERE_SPALARE is
begin														    
	process(USA,START)
	begin
		if (USA = '1' and REGIM_SELECTAT /= 'U' and CLATIRE /= 'U' and PRESPALARE /= 'U'  and TEMPERATURA /= 0 and VITEZA /= 801 and START = '1') then READY<='1';
		else READY<='0';
	end if;
	end process;
end ARC_03;

