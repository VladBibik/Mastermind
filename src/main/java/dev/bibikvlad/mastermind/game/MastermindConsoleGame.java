package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.data.GameOutcome;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;
import dev.bibikvlad.mastermind.persistence.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.input.validation.GameInputValidator;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final String answer;
    private final MastermindMessagePrinter printer;
    private final MastermindUserInputParser parser;
    private final LogoColorsBundle logoColorsBundle;

    private final GameStateManager gameStateManager;
    private final GameCommandHandler gameCommandHandler;
    private final GuessEvaluator guessEvaluator;

    public MastermindConsoleGame(MastermindMessagePrinter printer,
                                 MastermindUserInputParser inputParser,
                                 String answer,
                                 LogoColorsBundle logoColorsBundle) {
        this.printer = printer;
        this.parser = inputParser;
        this.answer = answer;
        this.logoColorsBundle = logoColorsBundle;

        gameStateManager = new GameStateManager(MAX_TURNS);
        gameCommandHandler = new GameCommandHandler(printer);
        guessEvaluator = new GuessEvaluator(answer, printer);
    }

    public GameOutcome play() {
        printLogoAndRules();

        while (true) {
            if (gameStateManager.isOver()) {
                printer.printGameOverMessage(answer);

                return new GameOutcome(gameStateManager.getCurrentTurn(), GameResult.LOSE);
            }

            String userInput = parser.parseUserInput();

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
                printer.printInvalidInputMessage();
            }
        }
    }

    private void printLogoAndRules() {
        printer.printAsciiLogo(logoColorsBundle);
        printer.printRulesMessage();
    }
}
