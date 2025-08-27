package dev.bibikvlad.utils.strings;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

public class ConsoleColoredValidSymbols {
    public static String getSymbols() {
        return ConsoleColor.BRIGHT_RED.getCode() + "r "
                + ConsoleColor.BRIGHT_GREEN.getCode() + "g "
                + ConsoleColor.BRIGHT_YELLOW.getCode() + "y "
                + ConsoleColor.BRIGHT_BLUE.getCode() + "b "
                + ConsoleColor.BRIGHT_PURPLE.getCode() + "p "
                + ConsoleColor.BRIGHT_WHITE.getCode() + "w"
                + ConsoleColor.RESET.getCode();
    }
}
