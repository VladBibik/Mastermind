package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class StubGameMessages implements GameMessages {
    @Override
    public String getInvalidInputMessage() {
        return "";
    }

    @Override
    public String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        return "";
    }

    @Override
    public String getGameOverMessage(String answer) {
        return "";
    }

    @Override
    public String getWinMessage(String answer) {
        return "Win! " + answer;
    }

    @Override
    public String getRulesMessage() {
        return "";
    }

    @Override
    public String getAsciiLogo() {
        return "";
    }
}
