package dev.bibikvlad.utils.strings;

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
