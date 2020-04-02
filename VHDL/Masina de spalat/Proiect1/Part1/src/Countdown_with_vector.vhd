library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all; 
use ieee.std_logic_unsigned.all;

entity COUNTDOWN_VECTOR	 is
	port(
		clk     : in 	STD_LOGIC	:='0';
		ENABLE  : in	STD_LOGIC	:='0';
		TIMP2	: in 	std_logic_vector(6 downto 0);
		seconds : inout std_logic_vector(6 downto 0);
     	minutes : inout std_logic_vector(6 downto 0);
     	hours   : inout std_logic_vector(6 downto 0)
	     );
end COUNTDOWN_VECTOR;		  		

architecture ARC_07 of COUNTDOWN_VECTOR is 
begin	
	process(clk) -- perioada de o secunda
	begin
		if ENABLE = '1' then
			if clk'event and clk='1' then
				if seconds > "0000000" then
					seconds <= seconds - "0000001";
				end if;
					if seconds = "0000000" and (minutes > "0000000" or hours > "0000000") then
						seconds <= "0111011";
					if minutes > "0000000" then
						minutes<=minutes - "0000001";
					end if;
						if minutes = "0000000" and hours > "0000000" then
							minutes <= "0111011";
						if hours > "0000000" then
							hours <= hours - "0000001";
						end if;
						end if;
					end if;
			end if;
		else
			if TIMP2 = "0101000" then -- 40
				seconds <= "0000000";
				minutes <= "0101000";
				hours	<= "0000000";
			end if;
			if TIMP2 = "0110010" then  --50
				seconds <= "0000000";
				minutes <= "0110010";
				hours	<= "0000000";
			end if;	
			if TIMP2 = "0111100" then  --60
				seconds <= "0000000";
				minutes <= "0000000";
				hours	<= "0000001";
			end if;	
			if TIMP2 = "1000110" then  --70
				seconds <= "0000000";
				minutes <= "0001010";
				hours	<= "0000001";
			end if;	
		end if;
	end process;
end ARC_07;
