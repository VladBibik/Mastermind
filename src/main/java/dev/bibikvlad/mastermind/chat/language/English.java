package dev.bibikvlad.mastermind.chat.language;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.utils.StringConstants.ConsoleColors;
import dev.bibikvlad.utils.StringConstants.Emojis;

public class English extends Language {
    @Override
    public String getInvalidInputMessage() {
        return "Invalid guess. Must include only letters: "
                + ConsoleColors.BrightForeground.RED + "r "
                + ConsoleColors.BrightForeground.GREEN + "g "
                + ConsoleColors.BrightForeground.YELLOW + "y "
                + ConsoleColors.BrightForeground.BLUE + "b "
                + ConsoleColors.BrightForeground.PURPLE + "p "
                + ConsoleColors.BrightForeground.WHITE + "w"
                + ConsoleColors.RESET;
    }

    @Override
    public String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        return "Turn " + (currentTurn + 1) + " out of " + maxTurns
                + ". Your guess: " + userInput
                + "\n" + getGuessVisualRepresentation(answer, userInput);
    }

    @Override
    public String getGameOverMessage(String answer) {
        return "Game Over! The solution was: "
                + InputVisualRepresentation.getVisualRepresentation(answer);
    }

    @Override
    public String getWinMessage(String answer) {
        return "You Won! " + Emojis.CELEBRATION_TADA
                + "\nYou are the Mastermind!"
                + "\nSolution was: " + InputVisualRepresentation.getVisualRepresentation(answer);
    }
}
