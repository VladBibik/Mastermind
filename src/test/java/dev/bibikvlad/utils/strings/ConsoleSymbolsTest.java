package dev.bibikvlad.utils.strings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleSymbolsTest {
    @Test
    @DisplayName("CIRCLE_SOLID call returns a correct symbol")
    void circleSolidReturnsCorrectSymbol() {
        char expected = '⬤';

        assertEquals(ConsoleSymbols.CIRCLE_SOLID, expected);
    }
}
