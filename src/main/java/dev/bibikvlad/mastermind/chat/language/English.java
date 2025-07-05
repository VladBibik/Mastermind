package dev.bibikvlad.mastermind.chat.language;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.utils.StringConstants.ConsoleColors;

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
    public String getTurnCounterAndUserGuess(int maxTurns, int currentTurn, String userInput) {
        return "Turn " + (currentTurn + 1) + " out of " + maxTurns
                + ". Your guess: " + userInput;
    }

    @Override
    public String getGameOverMessage(String answer) {
        return "Game Over! The solution is: " + InputVisualRepresentation.getVisualRepresentation(answer);
    }
}
