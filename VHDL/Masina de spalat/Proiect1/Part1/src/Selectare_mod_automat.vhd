library IEEE;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;
--Selectarea unui mod automat prestabilit 
entity SELECTARE_MOD_AUTOMAT is
	Port (
			AM,START: 			in  	STD_LOGIC;
			CLK:				in 		STD_LOGIC;
			MOD_SPALARE: 		inout 	STD_LOGIC_VECTOR (2 downto 0):="000");
end SELECTARE_MOD_AUTOMAT;

architecture ARC_06 of SELECTARE_MOD_AUTOMAT is
begin
	process(AM,CLK,MOD_SPALARE)
	begin
		if AM = '1' and START /= '1' then
			if CLK'event and CLK='1' then
				if MOD_SPALARE < "100" then
					MOD_SPALARE <= MOD_SPALARE + "001";
				else
					MOD_SPALARE <= "000";
				end if;
			end if;
		else
			MOD_SPALARE <= "111";
		end if;
	end process;
end ARC_06;



		--"000" when s="000" else	 --Spalare rapida
		--"001" when s="001" else	 --Camasi
		--"010" when s="010" else	 --Culori inchise
		--"011" when s="011" else	 --Rufe murdare	
		--"100" when s="100" else	 --Antialergic
		--"111";