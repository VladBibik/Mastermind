import dev.bibikvlad.mastermind.clues.ClueGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClueGeneratorTest {
    @Test
    @DisplayName("All correct positions and colors (full match)")
    void testAllCorrect() {
        assertEquals("◍◍◍◍", ClueGenerator.generateClue("rgby", "rgby"));
    }

    @Test
    @DisplayName("All correct positions and colors (full match)")
    void testThreeCorrectAndCorrectPositionOneWrong() {
        assertEquals("◍◍◍_", ClueGenerator.generateClue("rgby", "rgbb"));
    }

    @Test
    @DisplayName("Duplicate colors in guess exceeding answer")
    void testTooManySameColorInGuess() {
        assertEquals("◍◍__", ClueGenerator.generateClue("rrgb", "rrrr"));
    }

    @Test
    @DisplayName("One correct and in correct position, three wrong")
    void testOneCorrectAndInCorrectPositionThreeWrong() {
        assertEquals("◍___", ClueGenerator.generateClue("rpgb", "ypwp"));
    }

    @Test
    @DisplayName("Correct colors but all wrong positions")
    void testAllCorrectColorsWrongPositions() {
        assertEquals("○○○○", ClueGenerator.generateClue("rgby", "ybrg"));
    }

    @Test
    @DisplayName("Three correct colors but all wrong positions")
    void testThreeCorrectColorsWrongPositions() {
        assertEquals("○○○_", ClueGenerator.generateClue("rgby", "ybrw"));
    }

    @Test
    @DisplayName("All colors wrong")
    void testAllWrong() {
        assertEquals("____", ClueGenerator.generateClue("rgby", "wwww"));
    }

    @Test
    @DisplayName("Two correct, one correct but wrong place, one wrong")
    void testMixedClues() {
        assertEquals("◍◍○_", ClueGenerator.generateClue("rgby", "rgpb"));
    }

    @Test
    @DisplayName("Duplicate colors in both guess and answer (balanced)")
    void testBalancedDuplicates() {
        assertEquals("◍○○_", ClueGenerator.generateClue("rrgb", "brrr"));
    }

    @Test
    @DisplayName("Duplicate colors in guess under answer")
    void testFewerDuplicatesInGuess() {
        assertEquals("◍○__", ClueGenerator.generateClue("rrrg", "ryyr"));
    }
}

