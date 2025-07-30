package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;
import dev.bibikvlad.mastermind.validators.GameInputValidator;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final String answer;
    private final MastermindMessagePrinter printer;
    private final MastermindUserInputParser parser;

    private boolean close = false;
    private int turnCounter = 0;

    public MastermindConsoleGame(MastermindMessagePrinter printer,
                                 MastermindUserInputParser inputParser,
                                 String answer) {
        this.printer = printer;
        this.parser = inputParser;
        this.answer = answer;

        printLogoAndRules();
    }

    public void play() {
        while (!close) {
            if (isGameOver()) {
                continue;
            }

            String userInput = parser.parseUserInput();

            if (isGameClosed(userInput)) {
                continue;
            }

            processUserInput(userInput);
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
