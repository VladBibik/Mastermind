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

        printLogoAndRules();
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

                processUserInput(userInput);
            }
        } catch (IOException exception) {
            outputWriter.println(exception.getMessage());
        } finally {
            outputWriter.flush();
        }
    }

    private void printLogoAndRules() {
        outputWriter.println(language.getAsciiLogo());
        outputWriter.println(language.getRulesMessage());
    }

    private boolean isGameOver() {
        if (turnCounter == MAX_TURNS) {
            outputWriter.println(language.getGameOverMessage(answer));

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

    private void processUserInput(String userInput) {
        if (GameInputValidator.isInputValid(userInput)) {
            handleUserGuess(userInput);

            turnCounter++;
        } else {
            outputWriter.println(language.getInvalidInputMessage());
        }
    }

    private void handleUserGuess(String userInput) {
        if (userInput.equals(answer)) {
            outputWriter.println(language.getWinMessage(answer));

            close = true;
        } else {
            outputWriter.println(language.getIncorrectGuessMessage(MAX_TURNS, turnCounter, answer, userInput));
        }
    }
}
