package dev.bibikvlad.mastermind.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomAnswerGeneratorTest {
    @Test
    @DisplayName("Correct answer length test")
    void correctAnswerLengthTest() {
        String answer = RandomAnswerGenerator.generate();

        assertEquals(4, answer.length());
    }
}
