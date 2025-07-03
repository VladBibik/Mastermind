package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.mastermind.model.enums.MastermindColors;
import dev.bibikvlad.utils.StringConstants.ConsoleColors;
import dev.bibikvlad.utils.StringConstants.GameCluesConstants;

public class UserInputVisualRepresentation {
    public static String getVisualRepresentation(String input) {
        StringBuilder visualRepresentation = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == MastermindColors.WHITE.getSymbol()) {
                visualRepresentation
                        .append(ConsoleColors.RESET)
                        .append(GameCluesConstants.CIRCLE_SOLID);
            } else{
                visualRepresentation
                        .append(MastermindColors.fromColorSymbol(input.charAt(i)).getConsoleColorCode())
                        .append(GameCluesConstants.CIRCLE_SOLID);
            }
        }

        return visualRepresentation.toString();
    }
}
