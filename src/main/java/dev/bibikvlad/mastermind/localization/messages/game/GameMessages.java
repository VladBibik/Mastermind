package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface GameMessages extends LocalizedMessages {
    String getInvalidInput();

    String getIncorrectGuess(int maxTurns, int currentTurn, String answer, String userInput);

    String getGameOver(String answer);

    String getWin(String answer);

    String getRules();
}
