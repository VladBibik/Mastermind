import dev.bibikvlad.mastermind.clues.ClueGeneratorTwo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClueGeneratorTest {
    @Test
    @DisplayName("All correct positions and colors (full match)")
    void testAllCorrect() {
        assertEquals("◍◍◍◍", ClueGeneratorTwo.evaluate("rgby", "rgby"));
    }

    @Test
    @DisplayName("Correct colors but all wrong positions")
    void testAllCorrectColorsWrongPositions() {
        assertEquals("○○○○", ClueGeneratorTwo.evaluate("rgby", "ybrg"));
    }

    @Test
    @DisplayName("Three correct colors but all wrong positions")
    void testThreeCorrectColorsWrongPositions() {
        assertEquals("○○○_", ClueGeneratorTwo.evaluate("rgby", "ybrw"));
    }

    @Test
    @DisplayName("All colors wrong")
    void testAllWrong() {
        assertEquals("____", ClueGeneratorTwo.evaluate("rgby", "wwww"));
    }

    @Test
    @DisplayName("Two correct, one correct but wrong place, one wrong")
    void testMixedClues() {
        assertEquals("◍◍○_", ClueGeneratorTwo.evaluate("rgby", "rgpb"));
    }

    @Test
    @DisplayName("Duplicate colors in guess exceeding answer")
    void testTooManySameColorInGuess() {
        assertEquals("◍◍__", ClueGeneratorTwo.evaluate("rrgb", "rrrr"));
    }

    @Test
    @DisplayName("Duplicate colors in both guess and answer (balanced)")
    void testBalancedDuplicates() {
        assertEquals("◍○○_", ClueGeneratorTwo.evaluate("rrgb", "brrr"));
    }

    @Test
    @DisplayName("Duplicate colors in guess under answer")
    void testFewerDuplicatesInGuess() {
        assertEquals("◍○__", ClueGeneratorTwo.evaluate("rrrg", "ryyr"));
    }
}

