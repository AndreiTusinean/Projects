----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01/13/2020 12:12:12 PM
-- Design Name: 
-- Module Name: keyboard_ctrl - Behavioral
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

entity keyboard_ctrl is
    Port ( clk : in STD_LOGIC;
           clr : in STD_LOGIC;
           ps2Clk : in STD_LOGIC;
           ps2Data : in STD_LOGIC;
           An, Seg: out std_logic_vector(7 downto 0);
           LED: out std_logic);
end keyboard_ctrl;

architecture keyboard_ctrl of keyboard_ctrl is
type state_type is (start, wait_clk_lo1, wait_clk_hi1, getkey1, wait_clk_lo2, wait_clk_hi2, getkey2, break_key, wait_clk_lo3, wait_clk_hi3, getkey3);
signal state                            : state_type;
signal ps2_clk_f, ps2_data_f            : std_logic;                        --output debouncer
signal shift1, shift2, shift3           : std_logic_vector(10 downto 0);    --cele 3 shiftere de 11 biti
signal keyval1, keyval2, keyval3        : std_logic_vector(7 downto 0);     --valorile de 8 biti din data ale celor 3 shifteri
signal bit_count                        : std_logic_vector(3 downto 0);                        --counter de biti
constant cnt_max                        : std_logic_vector(3 downto 0) := "1011"; --11   --maximul pt counter : 11 biti pentru cate un cod transmis

signal data:std_logic_vector(31 downto 0);
signal sync_signals : std_logic_vector(1 downto 0);
begin


    process(clk)
    begin
        if rising_edge(clk) then
            sync_signals(0) <= ps2Clk;
            sync_signals(1) <= ps2Data;
        end if;
    end process;

    D1 : entity WORK.debounce generic map(8) port map(clk, sync_signals(0), ps2_clk_f);
    D2 : entity WORK.debounce generic map(8) port map(clk, sync_signals(0), ps2_data_f);
    
    process(clk, clr)
    begin
        if (clr = '1') then
            state <= start;
            bit_Count <= (others => '0');
            shift1 <= (others => '0');
            shift2 <= (others => '0');
            shift3 <= (others => '0');
            keyval1 <= (others => '0'); 
            keyval2 <= (others => '0'); 
            keyval3 <= (others => '0'); 
        elsif rising_edge(clk) then
            case state is
                when start =>
                                if ps2_data_f = '1' then 
                                    state <= start;
                                else
                                    state <= wait_clk_lo1;  
                                end if;
                when wait_clk_lo1 =>
                                if bit_Count < cnt_max then 
                                    if ps2_clk_f = '1' then 
                                        state <= wait_clk_lo1;
                                    else
                                        state <= wait_Clk_Hi1;
                                        shift1 <= ps2_data_f & shift1(10 downto 1);
                                    end if;
                                else
                                     state <= getkey1; 
                                end if;
               when wait_Clk_Hi1 =>
                                if ps2_clk_f = '0' then
                                    state <= wait_Clk_Hi1;
                                else
                                    state <= wait_clk_lo1;
                                    bit_Count <= bit_Count + 1;
                                end if;
               when getkey1 => 
                                keyval1 <= shift1(8 downto 1);
                                bit_Count <=  (others => '0');
                                state <= wait_clk_lo2;   
               when wait_clk_lo2 =>
                                if bit_Count < cnt_max then
                                    if ps2_clk_f = '1' then
                                        state <= wait_clk_lo2;
                                    else
                                        state <= wait_Clk_Hi2;
                                        shift2 <= ps2_data_f & shift2(10 downto 1);
                                    end if;
                                else
                                     state <= getkey2; 
                                end if;
              when wait_Clk_Hi2 =>
                                if ps2_clk_f = '0' then
                                    state <= wait_Clk_Hi2;
                                else
                                    state <= wait_clk_lo2;
                                    bit_Count <= bit_Count + 1;
                                end if;
              when getkey2 =>
                                keyval2 <= shift2(8 downto 1);
                                bit_Count <=  (others => '0');
                                state <= break_key;
              when break_key =>
                                if keyval2 = X"F0" then
                                    state <= wait_clk_lo3;  
                                else
                                    if keyval1 = X"E0" then
                                        state <= wait_clk_lo1;
                                    else
                                        state <= wait_clk_lo2;
                                    end if;
                                end if;
              when wait_clk_lo3 =>
                                if bit_Count < cnt_max then
                                    if ps2_clk_f = '1' then
                                        state <= wait_clk_lo3;
                                    else
                                        state <= wait_Clk_Hi3;
                                        shift3 <= ps2_data_f & shift3(10 downto 1);
                                    end if;
                                else
                                     state <= getkey3;
                                end if;  
              when wait_Clk_Hi3 =>
                                if ps2_clk_f = '0' then
                                    state <= wait_Clk_Hi3;
                                else
                                    state <= wait_clk_lo3;
                                    bit_Count <= bit_Count + 1;
                                end if;
              when getkey3 =>
                                keyval3 <= shift3(8 downto 1);
                                bit_Count <=  (others => '0');
                                state <= wait_clk_lo1;
            end case;
        end if;  
    end process;
    
   Data(23 downto 0) <= keyval3 & keyval2 & keyval1; 
      
   process
   variable cnt : INTEGER := 0;
    begin
        LED <= '0';    --Ca default datele sunt corecte     
        if shift1(0) = '1' OR shift1(10) = '0' then  --start bit trebuie sa fie 0 si stop 1
            LED <= '1';
        else
            for i in 1 to 8 loop
                if shift1(i) = '1' then
                    cnt := cnt + 1;
                end if;
            end loop;
            if ((cnt mod 2) = 0 AND shift1(9) = '0') OR ((cnt mod 2) = 1 AND shift1(9) = '1') then
                LED <= '1'; --daca avem nr par de 1 atunci parity bit trebuie sa fie 1
            end if;         --daca avem nr imoar de 1 atunci parity bit trebuie sa fie 0
        end if;
    end process;
   
    sevenSeg: ENTITY WORK.displ7seg port map(clk, clr, Data, An, Seg);

end keyboard_ctrl;
