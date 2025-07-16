package dev.bibikvlad.mastermind.localization.messages;

import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

public interface GameMessages {
    String getInvalidInputMessage();

    String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput);

    String getGameOverMessage(String answer);

    String getWinMessage(String answer);

    String getRulesMessage();

    String getAsciiLogo();
}
