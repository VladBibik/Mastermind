package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.formatters.ConsoleColorFormatter;
import dev.bibikvlad.utils.StringConstants.ConsoleColors;
import dev.bibikvlad.utils.StringConstants.GameCluesConstants;

public class InputVisualRepresentation {
    public static String getVisualRepresentation(String input) {
        StringBuilder visualRepresentation = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            visualRepresentation
                    .append(ConsoleColorFormatter.getColorCode(input.charAt(i)))
                    .append(GameCluesConstants.CIRCLE_SOLID);
        }

        visualRepresentation.append(ConsoleColors.RESET);

        return visualRepresentation.toString();
    }
}
