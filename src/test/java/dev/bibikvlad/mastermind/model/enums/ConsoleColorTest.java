package dev.bibikvlad.mastermind.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("Throws IllegalArgument exception on invalid indices")
    void invalidIndexThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ConsoleColor.fromForegroundColorByIndex(-1));
        assertThrows(IllegalArgumentException.class, () -> ConsoleColor.fromForegroundColorByIndex(0));
        assertThrows(IllegalArgumentException.class, () -> ConsoleColor.fromForegroundColorByIndex(77));
        assertThrows(IllegalArgumentException.class, () -> ConsoleColor.fromForegroundColorByIndex(-100));
        assertThrows(IllegalArgumentException.class, () -> ConsoleColor.fromForegroundColorByIndex(999));
    }

    @Test
    @DisplayName("Correctly returns console codes")
    void returnsExpectedAnsiEscapeCodes() {
        assertEquals("\u001B[31m", ConsoleColor.RED.getCode());
        assertEquals("\u001B[93m", ConsoleColor.BRIGHT_YELLOW.getCode());
        assertEquals("\u001B[97m", ConsoleColor.BRIGHT_WHITE.getCode());
        assertEquals("\u001B[38;5;111m", ConsoleColor.SKY_BLUE.getCode());
        assertEquals("\u001B[38;5;170m", ConsoleColor.ORCHID.getCode());
        assertEquals("\u001B[42m", ConsoleColor.BACKGROUND_GREEN.getCode());
        assertEquals("\u001B[46m", ConsoleColor.BACKGROUND_CYAN.getCode());
        assertEquals("\u001B[0m", ConsoleColor.RESET.getCode());
    }

    @Test
    @DisplayName("Correctly return display name")
    void correctlyReturnsDisplayName() {
        assertEquals("Yellow", ConsoleColor.YELLOW.getDisplayName());
        assertEquals("Bright Blue", ConsoleColor.BRIGHT_BLUE.getDisplayName());
        assertEquals("Bright White", ConsoleColor.BRIGHT_WHITE.getDisplayName());
        assertEquals("Sky Blue", ConsoleColor.SKY_BLUE.getDisplayName());
        assertEquals("Lime", ConsoleColor.LIME.getDisplayName());
        assertEquals("Pink", ConsoleColor.PINK.getDisplayName());
        assertEquals("Extended Purple", ConsoleColor.EXTENDED_PURPLE.getDisplayName());
        assertEquals("Background Blue", ConsoleColor.BACKGROUND_BLUE.getDisplayName());
    }

    @Test
    @DisplayName("Correctly returns console color category")
    void correctlyReturnsConsoleColorCategory() {
        assertEquals(ConsoleColor.Category.FOREGROUND, ConsoleColor.RED.getCategory());
        assertEquals(ConsoleColor.Category.BACKGROUND, ConsoleColor.BACKGROUND_BLUE.getCategory());
        assertEquals(ConsoleColor.Category.RESET, ConsoleColor.RESET.getCategory());
    }

    @Test
    @DisplayName("Correctly returns localization key")
    void correctlyReturnsLocalizationKey() {
        assertEquals("blue", ConsoleColor.BLUE.getLocalizationKey());
        assertEquals("bright_red", ConsoleColor.BRIGHT_RED.getLocalizationKey());
        assertEquals("orange", ConsoleColor.ORANGE.getLocalizationKey());
        assertEquals("extended_cyan", ConsoleColor.EXTENDED_CYAN.getLocalizationKey());
        assertEquals("magenta", ConsoleColor.MAGENTA.getLocalizationKey());
        assertEquals("background_blue", ConsoleColor.BACKGROUND_BLUE.getLocalizationKey());
    }

    @Test
    @DisplayName("Category collection has the correct size")
    void categoryCollectionHasCorrectSize() {
        assertEquals(39, ConsoleColor.getForegroundColors().size());
        assertEquals(8, ConsoleColor.getBackgroundColors().size());
    }

    @Test
    @DisplayName("getByCategory returns the same collection as getFore/BackgroundColors")
    void getByCategoryReturnsTheSameCollection() {
        assertEquals(ConsoleColor.getForegroundColors(), ConsoleColor.getByCategory(ConsoleColor.Category.FOREGROUND));
        assertEquals(ConsoleColor.getBackgroundColors(), ConsoleColor.getByCategory(ConsoleColor.Category.BACKGROUND));
    }
}
