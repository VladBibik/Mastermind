import dev.bibikvlad.mastermind.clues.ClueGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClueGeneratorTest {
    @Test
    @DisplayName("All correct positions and colors (full match)")
    void testAllCorrect() {
        assertEquals("◍◍◍◍", ClueGenerator.evaluate("rgby", "rgby"));
    }

    @Test
    @DisplayName("All correct positions and colors (full match)")
    void testThreeCorrectAndCorrectPositionOneWrong() {
        assertEquals("◍◍◍_", ClueGenerator.evaluate("rgby", "rgbb"));
    }

    @Test
    @DisplayName("Duplicate colors in guess exceeding answer")
    void testTooManySameColorInGuess() {
        assertEquals("◍◍__", ClueGenerator.evaluate("rrgb", "rrrr"));
    }

    @Test
    @DisplayName("One correct and in correct position, three wrong")
    void testOneCorrectAndInCorrectPositionThreeWrong() {
        assertEquals("◍___", ClueGenerator.evaluate("rpgb", "ypwp"));
    }

    @Test
    @DisplayName("Correct colors but all wrong positions")
    void testAllCorrectColorsWrongPositions() {
        assertEquals("○○○○", ClueGenerator.evaluate("rgby", "ybrg"));
    }

    @Test
    @DisplayName("Three correct colors but all wrong positions")
    void testThreeCorrectColorsWrongPositions() {
        assertEquals("○○○_", ClueGenerator.evaluate("rgby", "ybrw"));
    }

    @Test
    @DisplayName("All colors wrong")
    void testAllWrong() {
        assertEquals("____", ClueGenerator.evaluate("rgby", "wwww"));
    }

    @Test
    @DisplayName("Two correct, one correct but wrong place, one wrong")
    void testMixedClues() {
        assertEquals("◍◍○_", ClueGenerator.evaluate("rgby", "rgpb"));
    }

    @Test
    @DisplayName("Duplicate colors in both guess and answer (balanced)")
    void testBalancedDuplicates() {
        assertEquals("◍○○_", ClueGenerator.evaluate("rrgb", "brrr"));
    }

    @Test
    @DisplayName("Duplicate colors in guess under answer")
    void testFewerDuplicatesInGuess() {
        assertEquals("◍○__", ClueGenerator.evaluate("rrrg", "ryyr"));
    }
}

