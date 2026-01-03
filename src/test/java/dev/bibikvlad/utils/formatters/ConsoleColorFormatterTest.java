package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.persistence.model.enums.ConsoleColor;
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
            case 'r' -> ConsoleColor.BRIGHT_RED.getCode();
            case 'g' -> ConsoleColor.BRIGHT_GREEN.getCode();
            case 'y' -> ConsoleColor.BRIGHT_YELLOW.getCode();
            case 'b' -> ConsoleColor.BRIGHT_BLUE.getCode();
            case 'p' -> ConsoleColor.BRIGHT_PURPLE.getCode();
            case 'w' -> ConsoleColor.BRIGHT_WHITE.getCode();
            default -> throw new IllegalStateException("Unexpected symbol: " + symbol);
        };

        assertEquals(expected, ConsoleColorFormatter.getColorCode(symbol));
    }
}
