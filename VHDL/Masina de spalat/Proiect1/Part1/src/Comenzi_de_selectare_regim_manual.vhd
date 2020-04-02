library IEEE;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

--Functionare condititonata de selectarea regimului de spalare ca unul manual
entity CLATIRE_SUPLIMENTARA is
	port(
	S,START		:in 	STD_LOGIC;					   
	YN			:out	STD_LOGIC);
end CLATIRE_SUPLIMENTARA;	  

library IEEE;
use ieee.std_logic_1164.all;

entity PRESPALARE is
	port(
	S,START		:in 	STD_LOGIC;					   
	YN			:out	STD_LOGIC);
end PRESPALARE;

architecture ARC_01 of CLATIRE_SUPLIMENTARA is
begin
	process(S)
	begin
		if START /= '1' then
			if S = '1' then 
				YN <= '1';
			elsif S = '0' then
				YN <= '0';
			end if;
		end if;
	end process;
end ARC_01;

architecture ARC_02 of PRESPALARE is
begin
	process(S)
	begin
		if START /= '1' then
			if S = '1' then 
				YN <= '1';
			elsif S = '0' then
				YN <= '0';
			end if;
		end if;
	end process;
end ARC_02;
