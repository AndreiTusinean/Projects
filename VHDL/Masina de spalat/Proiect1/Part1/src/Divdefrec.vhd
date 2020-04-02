library	 IEEE;
use IEEE.STD_LOGIC_1164.all;
 --clk   reprezinta TACTUL de intrare cu frecventa de 100MHz
 --clk1 reprezinta TACTUL de iesire cu frecventa de 1Hz	 
entity DIV is
	port(
	clk:in STD_LOGIC;
	clk1:out STD_LOGIC
	);         
end entity;

architecture ARH_13 of DIV is
signal a:integer:=0;
signal b:STD_LOGIC:='0';
begin
	process
	begin
	 if clk='1' and clk'event then
	  a<=a+1;
	   if a=3 and b='0' then
		   b<='1'; a<=0;
	   elsif a=3 and b='1' then
		   b<='0'; a<=0;
	   end if;
	 end if;
	 clk1<=b;
	 wait on clk;
	end process;	
end architecture;	
	