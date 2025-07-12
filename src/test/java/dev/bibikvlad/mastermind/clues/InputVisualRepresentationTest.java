package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.strings.ConsoleColors;
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
}
