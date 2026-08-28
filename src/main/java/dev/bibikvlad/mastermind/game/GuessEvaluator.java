package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.output.GameOutput;

public class GuessEvaluator {
    private final GameOutput gameOutput;
    private final String correctAnswer;

    public GuessEvaluator(GameOutput gameOutput, String correctAnswer) {
        this.gameOutput = gameOutput;
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluateGuess(String userInput, int turn, int maxTurns) {
        if (userInput.equals(correctAnswer)) {
            gameOutput.printWin(correctAnswer);

            return true;
        } else {
            gameOutput.printIncorrectGuess(maxTurns, turn, correctAnswer, userInput);

            return false;
        }
    }
}
