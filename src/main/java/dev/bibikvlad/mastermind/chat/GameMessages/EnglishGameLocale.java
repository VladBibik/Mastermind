package dev.bibikvlad.mastermind.chat.GameMessages;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.utils.StringConstants.Emojis;
import dev.bibikvlad.utils.StringConstants.GameCluesConstants;

public class EnglishGameLocale extends GameMessagesLocale {
    @Override
    public String getInvalidInputMessage() {
        return "Invalid guess. Must include only letters: " + getColorChoices();
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

    @Override
    public String getRulesMessage() {
        return """
                Puzzle contains 4 boxes. Each turn you choose from 6 colors.
                Color choices: %s
                Example turn: ybgr
                Response:
                %s\tcorrect color in correct position
                %s\tcorrect color in incorrect position
                %s\tincorrect color
                
                The order of the response tiles does not necessarily match the colored characters.
                Type 'help', or 'rules' to read these instructions again
                Type 'close', or 'exit' to quit and show solution.
                """
                .formatted(
                        getColorChoices(),
                        GameCluesConstants.CIRCLE_SHADED,
                        GameCluesConstants.CIRCLE_EMPTY,
                        GameCluesConstants.UNDERSCORE
                );
    }
}
