library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity AFISOR is
port (
minu : in std_logic_vector(7 downto 0);
hr: in std_logic_vector(7 downto 0);
clk: in std_logic;
anod: out std_logic_vector(3 downto 0);
catod: out std_logic_vector(7 downto 0)
);
end AFISOR;

architecture ARH_12 of AFISOR is	
	signal clk_div:integer:=0;
	signal minu_idx:bit:='0';
	signal hr_idx:bit:='0';
	signal segs:std_logic_vector(7 downto 0):=("11111111");
begin
	
	process(clk)
	begin
		if(rising_edge(clk)) then
			if (clk_div=4999) then
				clk_div<=0;
		
				if(hr_idx='0') then
					
					if(minu_idx='0') then
						anod <= "0111";
					else
						anod <= "1011";
					end if;
				else
					if(minu_idx='0') then
						anod <= "1101";
					else
						anod <= "1110";
					end if;
				end if;
				if(hr_idx='0') then
					if(minu_idx='0') then
						case minu(7 downto 4) is
							when "0000" => segs <= "00000011"; -- 0
							when "0001" => segs <= "10011111"; -- 1
							when "0010" => segs <= "00100101"; -- 2
							when "0011" => segs <= "00001101"; -- 3
							when "0100" => segs <= "10011001"; -- 4
							when "0101" => segs <= "01001001"; -- 5
							when "0110" => segs <= "01000001"; -- 6   
							when "0111" => segs <= "00011111"; -- 7
							when "1000" => segs <= "00000001"; -- 8
							when "1001" => segs <= "00001001"; -- 9
							when others => segs <= "11111111"; -- err
						end case;		
						catod<=segs;
					else
						case minu(3 downto 0) is
							when "0000" => segs <= "00000011"; -- 0
							when "0001" => segs <= "10011111"; -- 1
							when "0010" => segs <= "00100101"; -- 2
							when "0011" => segs <= "00001101"; -- 3
							when "0100" => segs <= "10011001"; -- 4
							when "0101" => segs <= "01001001"; -- 5
							when "0110" => segs <= "01000001"; -- 6   
							when "0111" => segs <= "00011111"; -- 7
							when "1000" => segs <= "00000001"; -- 8
							when "1001" => segs <= "00001001"; -- 9
							when others => segs <= "11111111"; -- err
							end case;		
							catod<=segs;
					end if;
					minu_idx<=not(minu_idx);
				
				else 
					if(minu_idx='0') then
						case hr(7 downto 4) is
							when "0000" => segs <= "00000011"; -- 0
							when "0001" => segs <= "10011111"; -- 1
							when "0010" => segs <= "00100101"; -- 2
							when "0011" => segs <= "00001101"; -- 3
							when "0100" => segs <= "10011001"; -- 4
							when "0101" => segs <= "01001001"; -- 5
							when "0110" => segs <= "01000001"; -- 6   
							when "0111" => segs <= "00011111"; -- 7
							when "1000" => segs <= "00000001"; -- 8
							when "1001" => segs <= "00001001"; -- 9
							when others => segs <= "01110001"; -- err
						end case;		
						catod<=segs;
					else 
						case hr(3 downto 0) is
							when "0000" => segs <= "00000011"; -- 0
							when "0001" => segs <= "10011111"; -- 1
							when "0010" => segs <= "00100101"; -- 2
							when "0011" => segs <= "00001101"; -- 3
							when "0100" => segs <= "10011001"; -- 4
							when "0101" => segs <= "01001001"; -- 5
							when "0110" => segs <= "01000001"; -- 6   
							when "0111" => segs <= "00011111"; -- 7
							when "1000" => segs <= "00000001"; -- 8
							when "1001" => segs <= "00001001"; -- 9
							when others => segs <= "11111111"; -- err
						end case;		
						catod<=segs;
					end if;
					minu_idx<=not(minu_idx);
				end if;
				if minu_idx='1' then 
					hr_idx<=not(hr_idx);
				end if;
			else
				clk_div<=clk_div+1;
			end if;
					
		end if;	  
	end process;
end architecture;	