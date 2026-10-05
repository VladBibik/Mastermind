package dev.bibikvlad.utils.strings.valid;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleColoredValidSymbolsTest {
    @Test
    @DisplayName("Returns colored valid symbols")
    void returnsColoredValidSymbols() {
        assertEquals(
                ConsoleColor.BRIGHT_RED.getCode() + "r "
                        + ConsoleColor.BRIGHT_GREEN.getCode() + "g "
                        + ConsoleColor.BRIGHT_YELLOW.getCode() + "y "
                        + ConsoleColor.BRIGHT_BLUE.getCode() + "b "
                        + ConsoleColor.BRIGHT_PURPLE.getCode() + "p "
                        + ConsoleColor.BRIGHT_WHITE.getCode() + "w"
                        + ConsoleColor.RESET.getCode(),
                ConsoleColoredValidSymbols.getSymbols()
        );
    }
}
