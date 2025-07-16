package dev.bibikvlad.mastermind.localization.messages.temp_utils;

import dev.bibikvlad.utils.strings.ConsoleColors;

public class ConsoleColoredValidSymbols {
    public static String getSymbols() {
        return ConsoleColors.BrightForeground.RED + "r "
                + ConsoleColors.BrightForeground.GREEN + "g "
                + ConsoleColors.BrightForeground.YELLOW + "y "
                + ConsoleColors.BrightForeground.BLUE + "b "
                + ConsoleColors.BrightForeground.PURPLE + "p "
                + ConsoleColors.BrightForeground.WHITE + "w"
                + ConsoleColors.RESET;
    }
}
