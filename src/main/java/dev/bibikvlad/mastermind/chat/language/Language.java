package dev.bibikvlad.mastermind.chat.language;

public abstract class Language {
    public abstract String getInvalidInputMessage();

    public abstract String getTurnCounterAndUserGuess(int maxTurns, int currentTurn, String userInput);

    public abstract String getGameOverMessage(String answer);

    public abstract String getWinMessage(String answer);
}
