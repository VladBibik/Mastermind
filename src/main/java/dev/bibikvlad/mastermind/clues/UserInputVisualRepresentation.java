package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.Formatters.ConsoleColorFormatter;
import dev.bibikvlad.utils.StringConstants.GameCluesConstants;

public class UserInputVisualRepresentation {
    public static String getVisualRepresentation(String input) {
        StringBuilder visualRepresentation = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            visualRepresentation
                    .append(ConsoleColorFormatter.getColorCode(input.charAt(i)))
                    .append(GameCluesConstants.CIRCLE_SOLID);
        }

        return visualRepresentation.toString();
    }
}
