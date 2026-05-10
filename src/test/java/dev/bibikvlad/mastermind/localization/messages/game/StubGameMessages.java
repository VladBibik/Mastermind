package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public class StubGameMessages implements GameMessages {
    @Override
    public String getInvalidInput() {
        return "";
    }

    @Override
    public String getIncorrectGuess(int maxTurns, int currentTurn, String answer, String userInput) {
        return "";
    }

    @Override
    public String getGameOver(String answer) {
        return "";
    }

    @Override
    public String getWin(String answer) {
        return "Win! " + answer;
    }

    @Override
    public String getRules() {
        return "";
    }

    @Override
    public String getAsciiLogo(LogoColorsBundle logoColorsBundle) {
        return "";
    }
}
