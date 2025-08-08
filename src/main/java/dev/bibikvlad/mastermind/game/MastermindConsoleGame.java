package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;
import dev.bibikvlad.mastermind.validators.GameInputValidator;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final String answer;
    private final MastermindMessagePrinter printer;
    private final MastermindUserInputParser parser;

    private final GameStateManager gameStateManager;
    private final GameCommandHandler gameCommandHandler;

    public MastermindConsoleGame(MastermindMessagePrinter printer,
                                 MastermindUserInputParser inputParser,
                                 String answer) {
        this.printer = printer;
        this.parser = inputParser;
        this.answer = answer;

        gameStateManager = new GameStateManager(MAX_TURNS);
        gameCommandHandler = new GameCommandHandler(printer);

        printLogoAndRules();
    }

    public void play() {
        while (!gameStateManager.isGameClosed()) {
            if (isGameOver()) {
                continue;
            }

            String userInput = parser.parseUserInput();

            if (gameCommandHandler.handle(userInput)) {
                gameStateManager.close();

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
        if (gameStateManager.isOver()) {
            printer.printGameOverMessage(answer);

            gameStateManager.close();

            return true;
        }

        return false;
    }

    private void processUserInput(String userInput) {
        if (GameInputValidator.isInputValid(userInput)) {
            handleUserGuess(userInput);

            gameStateManager.nextTurn();
        } else {
            printer.printInvalidInputMessage();
        }
    }

    private void handleUserGuess(String userInput) {
        if (userInput.equals(answer)) {
            printer.printWinMessage(answer);

            gameStateManager.close();
        } else {
            printer.printIncorrectGuessMessage(MAX_TURNS, gameStateManager.getCurrentTurn(), answer, userInput);
        }
    }
}
