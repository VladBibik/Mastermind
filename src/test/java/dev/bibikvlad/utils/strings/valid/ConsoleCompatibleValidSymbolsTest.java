package dev.bibikvlad.utils.strings.valid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleCompatibleValidSymbolsTest {
    @Test
    @DisplayName("Returns correct compatible valid symbols")
    void returnsCorrectCompatibleValidSymbols() {
        String expected = "r g y b p w";

        assertEquals(expected, ConsoleCompatibleValidSymbols.getSymbols());
    }
}
