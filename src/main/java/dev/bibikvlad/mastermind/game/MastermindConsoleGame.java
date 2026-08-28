package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.data.GameOutcome;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.game.output.GameOutput;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.GameInputValidator;

public class MastermindConsoleGame {
    private static final int MAX_TURNS = 10;

    private final GameOutput gameOutput;
    private final Parser parser;
    private final String answer;

    private final GameStateManager gameStateManager;
    private final GameCommandHandler gameCommandHandler;
    private final GuessEvaluator guessEvaluator;

    public MastermindConsoleGame(GameOutput gameOutput, Parser parser, String answer) {
        this.gameOutput = gameOutput;
        this.parser = parser;
        this.answer = answer;

        gameStateManager = new GameStateManager(MAX_TURNS);
        gameCommandHandler = new GameCommandHandler(gameOutput);
        guessEvaluator = new GuessEvaluator(gameOutput, answer);
    }

    public GameOutcome play() {
        printLogoAndRules();

        while (true) {
            if (gameStateManager.isOver()) {
                gameOutput.printGameOver(answer);

                return new GameOutcome(gameStateManager.getCurrentTurn(), GameResult.LOSE);
            }

            String userInput = parser.parse();

            if (gameCommandHandler.handle(userInput)) {
                return new GameOutcome(gameStateManager.getCurrentTurn(), GameResult.CANCELED);
            }

            if (GameInputValidator.isInputValid(userInput)) {
                boolean won = guessEvaluator.evaluateGuess(userInput, gameStateManager.getCurrentTurn(), MAX_TURNS);
                gameStateManager.nextTurn();

                if (won) {
                    return new GameOutcome(gameStateManager.getCurrentTurn(), GameResult.WIN);
                }
            } else {
                gameOutput.printInvalidInput();
            }
        }
    }

    private void printLogoAndRules() {
        gameOutput.printLogo();
        gameOutput.printRules();
    }
}
