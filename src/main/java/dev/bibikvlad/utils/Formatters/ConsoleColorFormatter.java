package dev.bibikvlad.utils.Formatters;

import dev.bibikvlad.utils.StringConstants.ConsoleColors;

import java.util.Map;

public class ConsoleColorFormatter {
    private static final Map<Character, String> COLOR_CODE_MAP = Map.of(
            'r', ConsoleColors.BrightForeground.RED,
            'g', ConsoleColors.BrightForeground.GREEN,
            'y', ConsoleColors.BrightForeground.YELLOW,
            'b', ConsoleColors.BrightForeground.BLUE,
            'p', ConsoleColors.BrightForeground.PURPLE,
            'w', ConsoleColors.BrightForeground.WHITE
    );

    public static String getColorCode(char symbol) {
        String colorCode = COLOR_CODE_MAP.get(symbol);

        if (colorCode == null) {
            throw new IllegalArgumentException("Unsupported color symbol: " + symbol);
        }

        return colorCode;
    }
}
