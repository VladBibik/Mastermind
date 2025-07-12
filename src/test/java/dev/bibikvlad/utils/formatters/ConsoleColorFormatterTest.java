package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.utils.strings.ConsoleColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleColorFormatterTest {
    @Test
    @DisplayName("Throws exception on invalid symbol")
    public void throwsExceptionOnInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> ConsoleColorFormatter.getColorCode('?'));
    }

    @Test
    @DisplayName("Test all symbols return correct console code")
    public void allSymbolsReturnCorrectConsoleCode() {
        String allSymbols = "rgybpw";

        for (char c : allSymbols.toCharArray()) {
            testSymbol(c);
        }
    }

    private void testSymbol(char symbol) {
        switch(symbol) {
            case 'r' ->
                    assertEquals(ConsoleColors.BrightForeground.RED, ConsoleColorFormatter.getColorCode('r'));
            case 'g' ->
                    assertEquals(ConsoleColors.BrightForeground.GREEN, ConsoleColorFormatter.getColorCode('g'));
            case 'y' ->
                    assertEquals(ConsoleColors.BrightForeground.YELLOW, ConsoleColorFormatter.getColorCode('y'));
            case 'b' ->
                    assertEquals(ConsoleColors.BrightForeground.BLUE, ConsoleColorFormatter.getColorCode('b'));
            case 'p' ->
                    assertEquals(ConsoleColors.BrightForeground.PURPLE, ConsoleColorFormatter.getColorCode('p'));
            case 'w' ->
                    assertEquals(ConsoleColors.BrightForeground.WHITE, ConsoleColorFormatter.getColorCode('w'));
        }
    }
}
