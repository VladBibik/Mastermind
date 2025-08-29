package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

import java.util.Map;
import java.util.Optional;

public class ConsoleColorFormatter {
    private static final Map<Character, String> COLOR_CODE_MAP = Map.of(
            'r', ConsoleColor.BRIGHT_RED.getCode(),
            'g', ConsoleColor.BRIGHT_GREEN.getCode(),
            'y', ConsoleColor.BRIGHT_YELLOW.getCode(),
            'b', ConsoleColor.BRIGHT_BLUE.getCode(),
            'p', ConsoleColor.BRIGHT_PURPLE.getCode(),
            'w', ConsoleColor.BRIGHT_WHITE.getCode()
    );

    public static String getColorCode(char symbol) {
        return Optional.ofNullable(COLOR_CODE_MAP.get(Character.toLowerCase(symbol)))
                .orElseThrow(() -> new IllegalArgumentException("Invalid symbol: " + symbol));
    }
}
