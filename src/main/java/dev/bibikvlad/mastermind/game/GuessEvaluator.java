package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;

public class GuessEvaluator {
    private final String correctAnswer;
    private final MastermindMessagePrinter printer;

    public GuessEvaluator(String correctAnswer, MastermindMessagePrinter printer) {
        this.correctAnswer = correctAnswer;
        this.printer = printer;
    }

    public boolean evaluate(String userInput, int turn, int maxTurns) {
        if (userInput.equals(correctAnswer)) {
            printer.printWinMessage(correctAnswer);

            return true;
        } else {
            printer.printIncorrectGuessMessage(maxTurns, turn, correctAnswer, userInput);

            return false;
        }
    }
}
