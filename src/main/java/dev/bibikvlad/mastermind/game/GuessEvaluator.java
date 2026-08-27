package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.app.game.mode.GamePresentation;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;

public class GuessEvaluator {
    private final String correctAnswer;
    private final Printer printer;
    private final GameMessages gameMessages;
    private final GamePresentation gamePresentation;

    public GuessEvaluator(Printer printer, GameMessages gameMessages,
                          GamePresentation gamePresentation, String correctAnswer) {
        this.printer = printer;
        this.gameMessages = gameMessages;
        this.gamePresentation = gamePresentation;
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String userInput, int turn, int maxTurns) {
        if (userInput.equals(correctAnswer)) {
            String visualRepresentation = gamePresentation.getVisualRepresentation(correctAnswer);

            printer.printMessage(gameMessages.getWin(visualRepresentation));

            return true;
        } else {
            ClueSymbols clueSymbols = gamePresentation.getClueSymbols();

            printer.printMessage(gameMessages.getIncorrectGuess(clueSymbols, maxTurns, turn, correctAnswer, userInput));

            return false;
        }
    }
}
