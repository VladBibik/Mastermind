package dev.bibikvlad.mastermind.game.printer;

import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public interface MastermindMessagePrinter {
    void printInvalidInputMessage();

    void printIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput);

    void printGameOverMessage(String answer);

    void printWinMessage(String answer);

    void printRulesMessage();

    void printAsciiLogo(LogoColorsBundle logoColorsBundle);
}
