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

    @Test
    @DisplayName("Correctly returns background console color from the index")
    void validIndexReturnsCorrectBackgroundColor() {
        assertEquals(ConsoleColor.BACKGROUND_BLACK, ConsoleColor.fromBackgroundColorByIndex(1));
        assertEquals(ConsoleColor.BACKGROUND_GREEN, ConsoleColor.fromBackgroundColorByIndex(3));
        assertEquals(ConsoleColor.BACKGROUND_BLUE, ConsoleColor.fromBackgroundColorByIndex(5));
        assertEquals(ConsoleColor.BACKGROUND_CYAN, ConsoleColor.fromBackgroundColorByIndex(7));
        assertEquals(ConsoleColor.BACKGROUND_WHITE, ConsoleColor.fromBackgroundColorByIndex(8));
    }
}
