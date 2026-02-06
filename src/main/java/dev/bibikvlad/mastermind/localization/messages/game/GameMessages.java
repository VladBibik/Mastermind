package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public interface GameMessages extends LocalizedMessages {
    String getInvalidInputMessage();

    String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput);

    String getGameOverMessage(String answer);

    String getWinMessage(String answer);

    String getRulesMessage();

    String getAsciiLogo(LogoColorsBundle logoColorsBundle);
}
