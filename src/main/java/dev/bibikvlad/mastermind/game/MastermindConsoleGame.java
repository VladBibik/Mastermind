package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.validators.GameInputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final GameMessages gameMessages;
    private final String answer;
    private final BufferedReader inputReader;
    private final PrintStream outputWriter;

    private boolean close = false;
    private int turnCounter = 0;

    public MastermindConsoleGame(GameMessages language, String answer) {
        this(language, answer,
                new BufferedReader(new InputStreamReader(System.in)),
                System.out);
    }

    public MastermindConsoleGame(GameMessages language, String answer,
                                 BufferedReader inputReader, PrintStream outputWriter) {
        this.gameMessages = language;
        this.answer = answer;
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;

        printLogoAndRules();
    }

    public void play() {
        try {
            while (!close) {
                if (isGameOver()) {
                    continue;
                }

                //TODO: Move user input reader logic to the custom class, or the custom method!
                String userInput = inputReader.readLine().toLowerCase();

                if (isGameClosed(userInput)) {
                    continue;
                }

                processUserInput(userInput);
            }
        } catch (IOException exception) {
            outputWriter.println(exception.getMessage());
        } finally {
            outputWriter.flush();
        }
    }

    private void printLogoAndRules() {
        outputWriter.println(gameMessages.getAsciiLogo());
        outputWriter.println(gameMessages.getRulesMessage());
    }

    private boolean isGameOver() {
        if (turnCounter == MAX_TURNS) {
            outputWriter.println(gameMessages.getGameOverMessage(answer));

            close = true;

            return true;
        }

        return false;
    }

    private boolean isGameClosed(String userInput) {
        if ("close".equalsIgnoreCase(userInput) || "exit".equalsIgnoreCase(userInput)) {
            close = true;

            return true;
        }

        return false;
    }

    private void processUserInput(String userInput) {
        if (GameInputValidator.isInputValid(userInput)) {
            handleUserGuess(userInput);

            turnCounter++;
        } else {
            outputWriter.println(gameMessages.getInvalidInputMessage());
        }
    }

    private void handleUserGuess(String userInput) {
        if (userInput.equals(answer)) {
            outputWriter.println(gameMessages.getWinMessage(answer));

            close = true;
        } else {
            outputWriter.println(gameMessages.getIncorrectGuessMessage(MAX_TURNS, turnCounter, answer, userInput));
        }
    }
}
