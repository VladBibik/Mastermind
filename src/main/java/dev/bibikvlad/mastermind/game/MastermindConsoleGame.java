package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.validators.GameInputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final BufferedReader inputReader;
    private final PrintStream outputWriter;
    private final String answer;

    private boolean close = false;
    private int turnCounter = 0;

    public MastermindConsoleGame(String answer) {
        this(new BufferedReader(new InputStreamReader(System.in)),
                System.out,
                answer);
    }

    public MastermindConsoleGame(BufferedReader inputReader, PrintStream outputWriter, String answer) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.answer = answer;
    }

    public void play() {
        try {
            while (!close) {
                if (isGameOver()) {
                    continue;
                }

                outputWriter.println("Turn:" + (turnCounter + 1));

                String userInput = inputReader.readLine();

                if (isGameClosed(userInput)) {
                    continue;
                }

                if (!processUserInput(userInput)) {
                    continue;
                }

                handleUserGuess(userInput);
            }
        } catch (IOException exception) {
            outputWriter.println(exception.getMessage());
        }
    }

    private boolean isGameOver() {
        if (turnCounter == MAX_TURNS) {
            outputWriter.println(answer);
            outputWriter.println("You lose");

            close = true;

            return true;
        }

        return false;
    }

    private boolean isGameClosed(String userInput) {
        if ("close".equalsIgnoreCase(userInput)) {
            close = true;

            return true;
        }

        return false;
    }

    private boolean processUserInput(String userInput) {
        if (GameInputValidator.isInputValid(userInput)) {
            turnCounter++;

            return true;
        } else {
            outputWriter.println("Please provide a valid input");

            return false;
        }
    }

    private void handleUserGuess(String userInput) {
        if (userInput.equals(answer)) {
            outputWriter.println("You Won!");

            close = true;
        } else {
            outputWriter.println(ClueGenerator.generate(answer, userInput));
        }
    }
}
