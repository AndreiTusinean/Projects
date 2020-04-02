library	ieee;
use ieee.std_logic_1164.all;

entity DECOD is
	port( Intrare : in STD_LOGIC_VECTOR(6 downto 0);
	Iesire:out STD_LOGIC_VECTOR(7 downto 0));
end DECOD;

architecture ARC_11 of DECOD is
begin
	process (Intrare)
	begin
		case Intrare is
			when "0000000"=> Iesire<="00000000";--0
			when "0000001"=> Iesire<="00000001";--1
			when "0000010"=> Iesire<="00000010";--2
			when "0000011"=> Iesire<="00000011";--3
			when "0000100"=> Iesire<="00000100";--4
			when "0000101"=> Iesire<="00000101";--5
			when "0000110"=> Iesire<="00000110";--6
			when "0000111"=> Iesire<="00000111";--7
			when "0001000"=> Iesire<="00001000";--8
			when "0001001"=> Iesire<="00001001";--9
			when "0001010"=> Iesire<="00010000";--10
			when "0001011"=> Iesire<="00010001";--11
			when "0001100"=> Iesire<="00010010";--12
			when "0001101"=> Iesire<="00010011";--13
			when "0001110"=> Iesire<="00010100";--14
			when "0001111"=> Iesire<="00010101";--15
			when "0010000"=> Iesire<="00010110";--16
			when "0010001"=> Iesire<="00010111";--17
			when "0010010"=> Iesire<="00011000";--18
			when "0010011"=> Iesire<="00011001";--19
			when "0010100"=> Iesire<="00100000";--20
			when "0010101"=> Iesire<="00100001";--21
			when "0010110"=> Iesire<="00100010";--22
			when "0010111"=> Iesire<="00100011";--23
			when "0011000"=> Iesire<="00100100";--24
			when "0011001"=> Iesire<="00100101";--25
			when "0011010"=> Iesire<="00100110";--26
			when "0011011"=> Iesire<="00100111";--27
			when "0011100"=> Iesire<="00101000";--28
			when "0011101"=> Iesire<="00101001";--29
			when "0011110"=> Iesire<="00110000";--30
			when "0011111"=> Iesire<="00110001";--31
			when "0100000"=> Iesire<="00110010";--32
			when "0100001"=> Iesire<="00110011";--33
			when "0100010"=> Iesire<="00110100";--34
			when "0100011"=> Iesire<="00110101";--35
			when "0100100"=> Iesire<="00110110";--36
			when "0100101"=> Iesire<="00110111";--37
			when "0100110"=> Iesire<="00111000";--38
			when "0100111"=> Iesire<="00111001";--39
			when "0101000"=> Iesire<="00000000";--40
			when "0101001"=> Iesire<="00000000";--41
			when "0101010"=> Iesire<="00000000";--42
			when "0101011"=> Iesire<="00000000";--43
			when "0101100"=> Iesire<="00000000";--44
			when "0101101"=> Iesire<="00000000";--45
			when "0101110"=> Iesire<="00000000";--46
			when "0101111"=> Iesire<="00000000";--47
			when "0110000"=> Iesire<="00000000";--48
			when "0110001"=> Iesire<="00000000";--49
			when "0110010"=> Iesire<="00000000";--50
			when "0110011"=> Iesire<="00000000";--51
			when "0110100"=> Iesire<="00000000";--52
			when "0110101"=> Iesire<="00000000";--53
			when "0110110"=> Iesire<="00000000";--54
			when "0110111"=> Iesire<="00000000";--55
			when "0111000"=> Iesire<="00000000";--56
			when "0111001"=> Iesire<="00000000";--57
			when "0111010"=> Iesire<="00000000";--58
			when "0111011"=> Iesire<="00000000";--59
			when others=> Iesire<="00000000";--0
		end case;
	end process;
end ARC_11;