package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.game.data.GameOutcome;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.GameInputValidator;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final Printer printer;
    private final Parser parser;
    private final GameMessages gameMessages;
    private final String answer;
    private final String logo;

    private final GameStateManager gameStateManager;
    private final GameCommandHandler gameCommandHandler;
    private final GuessEvaluator guessEvaluator;

    public MastermindConsoleGame(Printer printer,
                                 Parser parser,
                                 GameMessages gameMessages,
                                 String answer,
                                 String logo) {
        this.printer = printer;
        this.parser = parser;
        this.gameMessages = gameMessages;
        this.answer = answer;
        this.logo = logo;

        gameStateManager = new GameStateManager(MAX_TURNS);
        gameCommandHandler = new GameCommandHandler(printer, gameMessages);
        guessEvaluator = new GuessEvaluator(printer, gameMessages, answer);
    }

    public GameOutcome play() {
        printLogoAndRules();

        while (true) {
            if (gameStateManager.isOver()) {
                printer.printMessage(gameMessages.getGameOver(answer));

                return new GameOutcome(gameStateManager.getCurrentTurn(), GameResult.LOSE);
            }

            String userInput = parser.parse();

            if (gameCommandHandler.handle(userInput)) {
                return new GameOutcome(gameStateManager.getCurrentTurn(), GameResult.CANCELED);
            }

            if (GameInputValidator.isInputValid(userInput)) {
                boolean won = guessEvaluator.evaluate(userInput, gameStateManager.getCurrentTurn(), MAX_TURNS);
                gameStateManager.nextTurn();

                if (won) {
                    return new GameOutcome(gameStateManager.getCurrentTurn(), GameResult.WIN);
                }
            } else {
                printer.printMessage(gameMessages.getInvalidInput());
            }
        }
    }

    private void printLogoAndRules() {
        printer.printMessage(logo);
        printer.printMessage(gameMessages.getRules());
    }
}
