package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public interface GameMessages extends LocalizedMessages {
    String getInvalidInput();

    String getIncorrectGuess(int maxTurns, int currentTurn, String answer, String userInput);

    String getGameOver(String answer);

    String getWin(String answer);

    String getRules();

    String getAsciiLogo(LogoColorsBundle logoColorsBundle);
}
