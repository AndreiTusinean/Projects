library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
--Selectarea temperaturii pentru modul de spalare manual
--TEMP=0 reprezinta valoarea default: daca nu a fost selectata temperatura atunci se va stii prin faptul ca TEMP=0
entity TEMPERATURA is
	port(
	CLK 				:in STD_LOGIC ;
	ENABLE,REGIM,START	:in STD_LOGIC ;
 	TEMP   				:inout INTEGER range 0 to 90 :=0);
end TEMPERATURA;		  		

architecture ARC_04 of TEMPERATURA is 
begin
	process(CLK,ENABLE,TEMP)
	begin
		if ENABLE = '1' and REGIM = '0' and START /= '1' then
			if CLK'event and CLK='1' then		 
				if(TEMP >= 90 or TEMP < 30 or TEMP = 0) then
					TEMP <= 30;
				else
					TEMP <= TEMP+30;
				end if;
			end if;
		end if;
	end process;
end ARC_04;