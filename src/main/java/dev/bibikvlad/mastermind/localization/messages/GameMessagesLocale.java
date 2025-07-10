package dev.bibikvlad.mastermind.localization.messages;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.utils.strings.AsciiLogo.ColoredAsciiLogo;
import dev.bibikvlad.utils.strings.ConsoleColors;

public abstract class GameMessagesLocale {
    public abstract String getInvalidInputMessage();

    public abstract String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput);

    public abstract String getGameOverMessage(String answer);

    public abstract String getWinMessage(String answer);

    public abstract String getRulesMessage();

    public String getAsciiLogo() {
        return ColoredAsciiLogo.getLogo();
    }

    protected String getGuessVisualRepresentation(String answer, String userInput) {
        return InputVisualRepresentation.getVisualRepresentation(userInput)
                + "        "
                + ClueGenerator.generate(answer, userInput);
    }

    protected String getColorChoices() {
        return ConsoleColors.BrightForeground.RED + "r "
                + ConsoleColors.BrightForeground.GREEN + "g "
                + ConsoleColors.BrightForeground.YELLOW + "y "
                + ConsoleColors.BrightForeground.BLUE + "b "
                + ConsoleColors.BrightForeground.PURPLE + "p "
                + ConsoleColors.BrightForeground.WHITE + "w"
                + ConsoleColors.RESET;
    }
}
