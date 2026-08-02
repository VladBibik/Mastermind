package dev.bibikvlad.mastermind.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleColorTest {
    @Test
    @DisplayName("Correctly returns enum from the index")
    void validIndexReturnsCorrectColor() {
        assertEquals(ConsoleColor.BLACK, ConsoleColor.fromForegroundColorByIndex(1));
        assertEquals(ConsoleColor.BRIGHT_YELLOW, ConsoleColor.fromForegroundColorByIndex(12));
        assertEquals(ConsoleColor.GOLD, ConsoleColor.fromForegroundColorByIndex(29));
        assertEquals(ConsoleColor.VIOLET, ConsoleColor.fromForegroundColorByIndex(34));
        assertEquals(ConsoleColor.LIGHT_GREY, ConsoleColor.fromForegroundColorByIndex(38));
    }
}
