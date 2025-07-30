package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;
import dev.bibikvlad.mastermind.validators.GameInputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final String answer;
    private final BufferedReader inputReader;
    private final MastermindMessagePrinter printer;

    private boolean close = false;
    private int turnCounter = 0;

    public MastermindConsoleGame(MastermindMessagePrinter printer, String answer) {
        this(answer,
                new BufferedReader(new InputStreamReader(System.in)),
                printer);
    }

    public MastermindConsoleGame(String answer,
                                 BufferedReader inputReader, MastermindMessagePrinter printer) {
        this.answer = answer;
        this.inputReader = inputReader;
        this.printer = printer;

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
            //TODO: Exception handling will be moved to the custom writer class
        }
    }

    private void printLogoAndRules() {
        printer.printAsciiLogo();
        printer.printRulesMessage();
    }

    private boolean isGameOver() {
        if (turnCounter == MAX_TURNS) {
            printer.printGameOverMessage(answer);

            close = true;

            return true;
        }

        return false;
    }

    private boolean isGameClosed(String userInput) {
        if ("close".equalsIgnoreCase(userInput) || "exit".equalsIgnoreCase(userInput)) {
            close = true;

            return true;
        } else if ("help".equalsIgnoreCase(userInput) || "rules".equalsIgnoreCase(userInput)) {
            printer.printRulesMessage();

            return false;
        }

        return false;
    }

    private void processUserInput(String userInput) {
        if (GameInputValidator.isInputValid(userInput)) {
            handleUserGuess(userInput);

            turnCounter++;
        } else {
            printer.printInvalidInputMessage();
        }
    }

    private void handleUserGuess(String userInput) {
        if (userInput.equals(answer)) {
            printer.printWinMessage(answer);

            close = true;
        } else {
            printer.printIncorrectGuessMessage(MAX_TURNS, turnCounter, answer, userInput);
        }
    }
}
