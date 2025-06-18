package dev.bibikvlad;

import java.util.Random;

public class RandomAnswerGenerator {
    private static final int NUMBER_OF_COLORS = 6;
    private static final Random random = new Random();

    public static String generate() {
        return answerGeneratorLoop();
    }

    private static String answerGeneratorLoop() {
        char[] chars = new char[NUMBER_OF_COLORS];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = generateRandomChar(
                    MastermindColors.fromColorIndex(random.nextInt(NUMBER_OF_COLORS)));
        }

        return String.valueOf(chars);
    }

    private static char generateRandomChar(MastermindColors mastermindColor) {
        return switch (mastermindColor) {
            case RED -> 'r';
            case GREEN -> 'g';
            case YELLOW -> 'y';
            case BLUE -> 'b';
            case PURPLE -> 'p';
            case WHITE -> 'w';
        };
    }
}
