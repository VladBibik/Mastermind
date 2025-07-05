package dev.bibikvlad.mastermind.chat.language;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;

public abstract class Language {
    public abstract String getInvalidInputMessage();

    public abstract String getTurnCounterAndUserGuess(int maxTurns, int currentTurn, String userInput);

    public abstract String getGameOverMessage(String answer);

    public abstract String getWinMessage(String answer);

    public String getIncorrectGuessMessage(String answer, String userInput) {
        return InputVisualRepresentation.getVisualRepresentation(userInput)
                + "        "
                + ClueGenerator.generate(answer, userInput);
    }
}
