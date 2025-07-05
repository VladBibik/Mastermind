package dev.bibikvlad.mastermind.chat.language;

public abstract class Language {
    public abstract String getInvalidInputMessage();

    public abstract String getTurnCounterAndUserGuess(int maxTurns, int currentTurn, String userInput);
}
