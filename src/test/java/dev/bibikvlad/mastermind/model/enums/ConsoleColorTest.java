package dev.bibikvlad.mastermind.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleColorTest {
    @Test
    @DisplayName("Correctly returns foreground console color from the index")
    void validIndexReturnsCorrectForegroundColor() {
        assertEquals(ConsoleColor.BLACK, ConsoleColor.fromForegroundColorByIndex(1));
        assertEquals(ConsoleColor.BRIGHT_YELLOW, ConsoleColor.fromForegroundColorByIndex(12));
        assertEquals(ConsoleColor.GOLD, ConsoleColor.fromForegroundColorByIndex(29));
        assertEquals(ConsoleColor.VIOLET, ConsoleColor.fromForegroundColorByIndex(34));
        assertEquals(ConsoleColor.LIGHT_GREY, ConsoleColor.fromForegroundColorByIndex(38));
    }
}
