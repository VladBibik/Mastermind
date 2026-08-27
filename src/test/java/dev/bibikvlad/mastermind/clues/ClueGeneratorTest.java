package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.strings.clue.ClueSymbols;
import dev.bibikvlad.utils.strings.clue.DefaultClueSymbols;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClueGeneratorTest {
    private static final ClueSymbols clueSymbols = new DefaultClueSymbols();

    @Test
    @DisplayName("All correct positions and colors (full match)")
    void testAllCorrect() {
        assertEquals("◍◍◍◍", ClueGenerator.generate(clueSymbols, "rgby", "rgby"));
    }

    @Test
    @DisplayName("All correct positions and colors (full match)")
    void testThreeCorrectAndCorrectPositionOneWrong() {
        assertEquals("◍◍◍_", ClueGenerator.generate(clueSymbols, "rgby", "rgbb"));
    }

    @Test
    @DisplayName("Duplicate colors in guess exceeding answer")
    void testTooManySameColorInGuess() {
        assertEquals("◍◍__", ClueGenerator.generate(clueSymbols, "rrgb", "rrrr"));
    }

    @Test
    @DisplayName("One correct and in correct position, three wrong")
    void testOneCorrectAndInCorrectPositionThreeWrong() {
        assertEquals("◍___", ClueGenerator.generate(clueSymbols, "rpgb", "ypwp"));
    }

    @Test
    @DisplayName("Correct colors but all wrong positions")
    void testAllCorrectColorsWrongPositions() {
        assertEquals("○○○○", ClueGenerator.generate(clueSymbols, "rgby", "ybrg"));
    }

    @Test
    @DisplayName("Three correct colors but all wrong positions")
    void testThreeCorrectColorsWrongPositions() {
        assertEquals("○○○_", ClueGenerator.generate(clueSymbols, "rgby", "ybrw"));
    }

    @Test
    @DisplayName("All colors wrong")
    void testAllWrong() {
        assertEquals("____", ClueGenerator.generate(clueSymbols, "rgby", "wwww"));
    }

    @Test
    @DisplayName("Two correct, one correct but wrong place, one wrong")
    void testMixedClues() {
        assertEquals("◍◍○_", ClueGenerator.generate(clueSymbols, "rgby", "rgpb"));
    }

    @Test
    @DisplayName("Duplicate colors in both guess and answer (balanced)")
    void testBalancedDuplicates() {
        assertEquals("◍○○_", ClueGenerator.generate(clueSymbols, "rrgb", "brrr"));
    }

    @Test
    @DisplayName("Duplicate colors in guess under answer")
    void testFewerDuplicatesInGuess() {
        assertEquals("◍○__", ClueGenerator.generate(clueSymbols, "rrrg", "ryyr"));
    }
}

