package dev.bibikvlad.mastermind.game.output;

import dev.bibikvlad.mastermind.game.mode.GamePresentation;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class GameOutput {
    private final Printer printer;
    private final GameMessages gameMessages;
    private final GamePresentation gamePresentation;

    public GameOutput(Printer printer, GameMessages gameMessages, GamePresentation gamePresentation) {
        this.printer = printer;
        this.gameMessages = gameMessages;
        this.gamePresentation = gamePresentation;
    }

    public void printLogo() {
        printer.printMessage(gamePresentation.getLogo());
    }

    public void printInvalidInput() {
        printer.printMessage(
                gameMessages.getInvalidInput(
                        gamePresentation.getValidSymbols()
                )
        );
    }

    public void printIncorrectGuess(int maxTurns, int currentTurn, String answer, String userInput) {
        printer.printMessage(
                gameMessages.getIncorrectGuess(maxTurns, currentTurn, answer, userInput,
                        ClueGenerator.generate(
                                gamePresentation.getClueSymbols(),
                                answer,
                                userInput
                        )
                )
        );
    }

    public void printGameOver(String answer) {
        printer.printMessage(
                gameMessages.getGameOver(
                        gamePresentation.getVisualRepresentation(answer)
                )
        );
    }

    public void printWin(String answer) {
        printer.printMessage(
                gameMessages.getWin(
                        gamePresentation.getVisualRepresentation(answer)
                )
        );
    }

    public void printRules() {
        printer.printMessage(gameMessages.getRules(
                        gamePresentation.getClueSymbols(),
                        gamePresentation.getValidSymbols()
                )
        );
    }
}
