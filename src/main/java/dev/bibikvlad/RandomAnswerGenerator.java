package dev.bibikvlad;

import java.util.Random;

public class RandomAnswerGenerator {
    private static final int NUMBER_OF_COLORS = 6;
    private static final Random random = new Random();

    public static String generateRandomAnswer() {
        return answerGeneratorLoop();
    }

    private static String answerGeneratorLoop() {
        char[] chars = new char[NUMBER_OF_COLORS];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = generateRandomChar(random.nextInt(NUMBER_OF_COLORS));
        }

        return String.valueOf(chars);
    }

    private static char generateRandomChar(int charIndex) {
        return switch (charIndex) {
            case 0 -> 'r';
            case 1 -> 'g';
            case 2 -> 'y';
            case 3 -> 'b';
            case 4 -> 'm';
            case 5 -> 'w';
            default -> throw new IllegalArgumentException("Invalid character index");
        };
    }
}
