library IEEE;
use ieee.std_logic_1164.all;
--Selectare modului de spalare : Automat sau Manual  	: Manual  pentru AM = 0 
--												   		: Automat pentru AM = 1

entity MOD_REGIM is
	port(
	S,START	:in 	STD_LOGIC;					   
	AM				:out	STD_LOGIC);
end MOD_REGIM;					

architecture ARC_00 of MOD_REGIM is
begin
	process(S)
	begin
		if START /= '1' then
			if S = '1' then 
				AM <= '1';
			elsif S = '0' then
				AM <= '0';
			end if;
		end if;
	end process;
end ARC_00;