package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;

public class GuessEvaluator {
    private final String correctAnswer;
    private final Printer printer;
    private final GameMessages gameMessages;
    private final ClueSymbols clueSymbols;

    public GuessEvaluator(Printer printer, GameMessages gameMessages, ClueSymbols clueSymbols, String correctAnswer) {
        this.printer = printer;
        this.gameMessages = gameMessages;
        this.clueSymbols = clueSymbols;
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String userInput, int turn, int maxTurns) {
        if (userInput.equals(correctAnswer)) {
            printer.printMessage(gameMessages.getWin(correctAnswer));

            return true;
        } else {
            printer.printMessage(gameMessages.getIncorrectGuess(clueSymbols, maxTurns, turn, correctAnswer, userInput));

            return false;
        }
    }
}
