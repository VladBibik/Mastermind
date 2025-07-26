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
        String allSymbols = "rgybpwRGYBPW";

        for (char c : allSymbols.toCharArray()) {
            testSymbol(c);
        }
    }

    private void testSymbol(char symbol) {
        String expected = switch (Character.toLowerCase(symbol)) {
            case 'r' -> ConsoleColors.BrightForeground.RED;
            case 'g' -> ConsoleColors.BrightForeground.GREEN;
            case 'y' -> ConsoleColors.BrightForeground.YELLOW;
            case 'b' -> ConsoleColors.BrightForeground.BLUE;
            case 'p' -> ConsoleColors.BrightForeground.PURPLE;
            case 'w' -> ConsoleColors.BrightForeground.WHITE;
            default -> throw new IllegalStateException("Unexpected symbol: " + symbol);
        };

        assertEquals(expected, ConsoleColorFormatter.getColorCode(symbol));
    }
}
