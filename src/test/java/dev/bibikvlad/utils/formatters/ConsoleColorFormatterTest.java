package dev.bibikvlad.utils.formatters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleColorFormatterTest {
    @Test
    @DisplayName("Throws exception on invalid symbol")
    public void throwsExceptionOnInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> ConsoleColorFormatter.getColorCode('?'));
    }
}
