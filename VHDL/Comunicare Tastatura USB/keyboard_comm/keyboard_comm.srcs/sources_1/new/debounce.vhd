----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01/13/2020 12:39:36 PM
-- Design Name: 
-- Module Name: debounce - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity debounce is
    generic(
        cnt_size : INTEGER := 19);
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC;
           result : out STD_LOGIC);
end debounce;

architecture Behavioral of debounce is
signal flipflops : std_logic_vector(1 downto 0);        --input
signal cnt_set   : std_logic;                           --reset
signal cnt_out   : std_logic_vector(19 downto 0) := (others => '0');   
begin

cnt_set <= flipflops(0) xor flipflops(1);

process(clk)
begin
    if(clk'event and clk = '1') then
        flipflops(0) <= btn;
        flipflops(1) <= flipflops(0);
        if(cnt_set = '1') then
            cnt_out <= (others => '0');
        elsif(cnt_out(cnt_size) = '0') then
            cnt_out <= cnt_out + 1;
        else
            result <= flipflops(1);
        end if;
     end if;
end process;

end Behavioral;
