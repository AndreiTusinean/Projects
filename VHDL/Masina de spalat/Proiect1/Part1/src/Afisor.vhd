library	ieee;
use ieee.std_logic_1164.all;

entity AFISOR is
	port( INTR : in STD_LOGIC_VECTOR(3 downto 0);
	BCD: out STD_LOGIC_VECTOR(6 downto 0));
end AFISOR;

architecture ARC_10 of AFISOR is
begin
	process(INTR)
	begin
		case INTR is 
			when "0000" =>BCD <= "1111110"; ---0
			when "0001" =>BCD <= "0110000"; ---1
			when "0010" =>BCD <= "1101101"; ---2
			when "0011" =>BCD <= "1111001"; ---3
			when "0100" =>BCD <= "0110011"; ---4
			when "0101" =>BCD <= "1011011"; ---5
			when "0110" =>BCD <= "1011111"; ---6
			when "0111" =>BCD <= "1110000"; ---7
			when "1000" =>BCD <= "1111111"; ---8
			when "1001" =>BCD <= "1111011"; ---9
			when others =>BCD <= "0000000"; ---null
		end case;
	end process;
end ARC_10;