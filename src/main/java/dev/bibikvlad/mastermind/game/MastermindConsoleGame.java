package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.chat.language.Language;
import dev.bibikvlad.mastermind.validators.GameInputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final Language language;
    private final String answer;
    private final BufferedReader inputReader;
    private final PrintStream outputWriter;

    private boolean close = false;
    private int turnCounter = 0;

    public MastermindConsoleGame(Language language, String answer) {
        this(language, answer,
                new BufferedReader(new InputStreamReader(System.in)),
                System.out);
    }

    public MastermindConsoleGame(Language language, String answer,
                                 BufferedReader inputReader, PrintStream outputWriter) {
        this.language = language;
        this.answer = answer;
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
    }

    public void play() {
        try {
            while (!close) {
                if (isGameOver()) {
                    continue;
                }

                String userInput = inputReader.readLine();

                if (isGameClosed(userInput)) {
                    continue;
                }

                outputWriter.println("Turn " + (turnCounter + 1) + " out of " + MAX_TURNS
                        + ". Your guess: " + userInput);

                if (!processUserInput(userInput)) {
                    continue;
                }

                handleUserGuess(userInput);
            }
        } catch (IOException exception) {
            outputWriter.println(exception.getMessage());
        } finally {
            outputWriter.flush();
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
            outputWriter.println(language.getInvalidInputMessage());

            return false;
        }
    }

    private void handleUserGuess(String userInput) {
        if (UserGuessHandler.handle(answer, userInput))
            close = true;
    }
}
