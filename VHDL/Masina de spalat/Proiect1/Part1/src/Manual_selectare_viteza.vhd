library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
--Selectarea vitezei pentru modul de spalare manual
--RPM=801 reprezinta valoarea default: daca nu a fost selectata viteza atunci se va stii prin faptul ca RPM=801
entity VITEZA is
	port(
	CLK 				:in STD_LOGIC				  ;
	ENABLE,REGIM,START	:in STD_LOGIC				  ;
	RPM					:inout INTEGER range 800 to 1200 :=801);
end VITEZA;		  		

architecture ARC_05 of VITEZA is 
begin
	process(CLK,ENABLE,REGIM)
	begin
		if ENABLE = '1' and REGIM = '0' and START /= '1' then
			if CLK'event and CLK='1' then
				if(RPM >= 1200 or RPM < 800 or RPM = 801) then
					RPM <= 800;
				else
					RPM <= RPM+200;
				end if;
			end if;
		end if;
	end process;
end ARC_05;