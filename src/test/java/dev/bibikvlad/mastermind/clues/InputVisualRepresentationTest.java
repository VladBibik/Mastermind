package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.utils.strings.GameCluesConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputVisualRepresentationTest {
    @Test
    @DisplayName("Testing visual representation for the single character")
    void testVisualRepresentationForSingleCharacter() {
        String expected = ConsoleColor.BRIGHT_RED.getCode()
                + GameCluesConstants.CIRCLE_SOLID
                + ConsoleColor.RESET.getCode();
        String actual = InputVisualRepresentation.getVisualRepresentation("R");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Testing visual representation for multiple characters")
    void testVisualRepresentationForMultipleCharacters() {
        String expected = ConsoleColor.BRIGHT_RED.getCode() + GameCluesConstants.CIRCLE_SOLID
                + ConsoleColor.BRIGHT_GREEN.getCode() + GameCluesConstants.CIRCLE_SOLID
                + ConsoleColor.BRIGHT_BLUE.getCode() + GameCluesConstants.CIRCLE_SOLID
                + ConsoleColor.RESET.getCode();

        String actual = InputVisualRepresentation.getVisualRepresentation("rgb");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Empty string test")
    void testVisualRepresentationForEmptyString() {
        String expected = ConsoleColor.RESET.getCode();
        String actual = InputVisualRepresentation.getVisualRepresentation("");

        assertEquals(expected, actual);
    }
}
