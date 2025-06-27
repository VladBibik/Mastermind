package dev.bibikvlad.mastermind.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomAnswerGeneratorTest {
    @Test
    @DisplayName("Correct answer length test")
    void correctAnswerLengthTest() {
        int correctAnswerLength = 4;
        String answer = RandomAnswerGenerator.generate();

        assertEquals(correctAnswerLength, answer.length());
    }

    @Test
    @DisplayName("The class generates string that matches correct pattern")
    void correctAnswerPatternTest() {
        String answer = RandomAnswerGenerator.generate();
        Pattern correctPattern = Pattern.compile("[rgybpw]{4}");

        assertTrue(correctPattern.matcher(answer).matches());
    }
}
