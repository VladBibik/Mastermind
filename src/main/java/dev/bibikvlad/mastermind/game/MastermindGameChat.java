package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.clues.ClueGeneratorTwo;
import dev.bibikvlad.utils.validators.UserGameInputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MastermindGameChat {
    private boolean close = false;
    private int turnCounter = 0;
    private final String answer;

    public MastermindGameChat() {
        answer = RandomAnswerGenerator.generate();
    }

    public void play() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
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

    private boolean isGameOver() {
        if (turnCounter == 10) {
            System.out.println(answer);
            System.out.println("You lose");

            close = true;

            return true;
        }

        return false;
    }

    private boolean isGameClosed(String userInput) {
        if (userInput.equals("close")) {
            close = true;

            return true;
        }

        return false;
    }

    private boolean isInputValid(String userInput) {
        if (UserGameInputValidator.isInputValid(userInput)) {
            turnCounter++;

            return true;
        } else {
            System.out.println("Please provide a valid input");

            return false;
        }
    }

    private void checkIfAnswerIsCorrect(String userInput) {
        if (userInput.equals(answer)) {
            System.out.println("You Won!");

            close = true;
        } else {
            System.out.println(ClueGeneratorTwo.evaluate(answer, userInput));
        }
    }
}
