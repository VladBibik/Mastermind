package dev.bibikvlad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern validInputPattern = Pattern.compile("[rgybpw]{4}");

    private static boolean close = false;
    private static int turnCounter = 0;
    private static String answer = "";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            answer = generateAnswerForCurrentSession();

            while (!close) {
                if (isGameOver()) {
                    continue;
                }

                System.out.println("Turn:" + (turnCounter + 1));

                String userInput = bufferedReader.readLine();

                if (isGameClosed(userInput)) {
                    continue;
                }

                if (!isInputValid(userInput)) {
                    continue;
                }

                checkIfAnswerIsCorrect(userInput);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static String generateAnswerForCurrentSession() {
        return RandomAnswerGenerator.generate();
    }

    private static boolean isGameOver() {
        if (turnCounter == 10) {
            System.out.println(answer);
            System.out.println("You lose");

            close = true;

            return true;
        }

        return false;
    }

    private static boolean isGameClosed(String userInput) {
        if (userInput.equals("close")) {
            close = true;

            return true;
        }

        return false;
    }

    private static boolean isInputValid(String userInput) {
        if (userInput.matches(validInputPattern.pattern())) {
            turnCounter++;

            return true;
        } else {
            System.out.println("Please provide a valid input");

            return false;
        }
    }

    private static void checkIfAnswerIsCorrect(String userInput) {
        if (userInput.equals(answer)) {
            System.out.println("You Won!");

            close = true;
        } else {
            System.out.println(ClueGenerator.evaluate(answer, userInput));
        }
    }
}
