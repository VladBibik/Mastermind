package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.strings.ConsoleColors;
import dev.bibikvlad.utils.strings.GameCluesConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputVisualRepresentationTest {
    @Test
    @DisplayName("Testing visual representation for the single character")
    void testVisualRepresentationForSingleCharacter() {
        String expected = "\u001B[91m⬤" + ConsoleColors.RESET;
        String actual = InputVisualRepresentation.getVisualRepresentation("r");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Testing visual representation for multiple characters")
    void testVisualRepresentationForMultipleCharacters() {
        String expected = ConsoleColors.BrightForeground.RED + GameCluesConstants.CIRCLE_SOLID
                + ConsoleColors.BrightForeground.GREEN + GameCluesConstants.CIRCLE_SOLID
                + ConsoleColors.BrightForeground.BLUE + GameCluesConstants.CIRCLE_SOLID
                + ConsoleColors.RESET;

        String actual = InputVisualRepresentation.getVisualRepresentation("rgb");
    }
}
