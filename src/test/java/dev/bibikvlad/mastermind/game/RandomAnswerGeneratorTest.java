package dev.bibikvlad.mastermind.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomAnswerGeneratorTest {
    private static final int ANSWER_LENGTH = 4;

    @Test
    @DisplayName("Correct answer length test")
    void correctAnswerLengthTest() {
        assertEquals(ANSWER_LENGTH, getRandomAnswer().length());
    }

    @Test
    @DisplayName("The class generates string that matches correct pattern")
    void correctAnswerPatternTest() {
        Pattern correctPattern = Pattern.compile("[rgybpw]{4}");

        assertTrue(correctPattern.matcher(getRandomAnswer()).matches());
    }

    private String getRandomAnswer() {
        return RandomAnswerGenerator.generate();
    }
}
