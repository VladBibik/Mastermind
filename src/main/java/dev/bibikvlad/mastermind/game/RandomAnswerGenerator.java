package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.persistence.model.enums.MastermindColors;

import java.util.Random;

public class RandomAnswerGenerator {
    private static final int ANSWER_LENGTH = 4;
    private static final int NUMBER_OF_COLORS = 6;
    private static final Random random = new Random();

    public static String generate() {
        return answerGeneratorLoop();
    }

    private static String answerGeneratorLoop() {
        char[] chars = new char[ANSWER_LENGTH];

        for (int i = 0; i < ANSWER_LENGTH; i++) {
            MastermindColors randomColor = MastermindColors.fromColorIndex(random.nextInt(NUMBER_OF_COLORS));

            chars[i] = randomColor.getSymbol();
        }

        return String.valueOf(chars);
    }
}
