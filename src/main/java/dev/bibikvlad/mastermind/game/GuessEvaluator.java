package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class GuessEvaluator {
    private final String correctAnswer;
    private final Printer printer;
    private final GameMessages gameMessages;

    public GuessEvaluator(Printer printer, GameMessages gameMessages, String correctAnswer) {
        this.printer = printer;
        this.gameMessages = gameMessages;
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String userInput, int turn, int maxTurns) {
        if (userInput.equals(correctAnswer)) {
            printer.printMessage(gameMessages.getWin(correctAnswer));

            return true;
        } else {
            printer.printMessage(gameMessages.getIncorrectGuess(maxTurns, turn, correctAnswer, userInput));

            return false;
        }
    }
}
