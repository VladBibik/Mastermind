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
    private final GuessEvaluator guessEvaluator;

    public MastermindConsoleGame(MastermindMessagePrinter printer,
                                 MastermindUserInputParser inputParser,
                                 String answer) {
        this.printer = printer;
        this.parser = inputParser;
        this.answer = answer;

        gameStateManager = new GameStateManager(MAX_TURNS);
        gameCommandHandler = new GameCommandHandler(printer);
        guessEvaluator = new GuessEvaluator(answer, printer);


        printLogoAndRules();
    }

    public void play() {
        while (!gameStateManager.isGameClosed()) {
            if (gameStateManager.isOver()) {
                printer.printGameOverMessage(answer);

                gameStateManager.close();

                continue;
            }

            String userInput = parser.parseUserInput();

            if (gameCommandHandler.handle(userInput)) {
                gameStateManager.close();

                continue;
            }

            if (GameInputValidator.isInputValid(userInput)) {
                boolean won = guessEvaluator.evaluate(userInput, gameStateManager.getCurrentTurn(),  MAX_TURNS);

                if (won) {
                    gameStateManager.close();
                } else  {
                    gameStateManager.nextTurn();
                }
            } else {
                printer.printInvalidInputMessage();
            }
        }
    }

    private void printLogoAndRules() {
        printer.printAsciiLogo();
        printer.printRulesMessage();
    }
}
