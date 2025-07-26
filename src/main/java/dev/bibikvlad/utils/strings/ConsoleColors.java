package dev.bibikvlad.utils.strings;

public class ConsoleColors {
    public static final class Foreground {
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
    }

    public static final class BrightForeground {
        public static final String BLACK = "\u001B[90m";
        public static final String RED = "\u001B[91m";
        public static final String GREEN = "\u001B[92m";
        public static final String YELLOW = "\u001B[93m";
        public static final String BLUE = "\u001B[94m";
        public static final String PURPLE = "\u001B[95m"; // or BRIGHT_MAGENTA
        public static final String CYAN = "\u001B[96m";
        public static final String WHITE = "\u001B[97m";
    }

    public static final class ExtendedAnsiForeground {
        public static final String ORANGE = "\u001B[38;5;208m";
        public static final String LIGHT_ORANGE = "\u001B[38;5;214m";
        public static final String DARK_ORANGE = "\u001B[38;5;166m";

        public static final String LIGHT_BLUE = "\u001B[38;5;117m";
        public static final String SKY_BLUE = "\u001B[38;5;111m";
        public static final String DARK_BLUE = "\u001B[38;5;19m";

        public static final String TEAL = "\u001B[38;5;37m";
        public static final String CYAN = "\u001B[38;5;51m";

        public static final String LIME = "\u001B[38;5;154m";
        public static final String GREEN = "\u001B[38;5;40m";
        public static final String DARK_GREEN = "\u001B[38;5;22m";

        public static final String YELLOW = "\u001B[38;5;226m";
        public static final String GOLD = "\u001B[38;5;220m";

        public static final String PINK = "\u001B[38;5;213m";
        public static final String HOT_PINK = "\u001B[38;5;205m";
        public static final String MAGENTA = "\u001B[38;5;201m";
        public static final String PURPLE = "\u001B[38;5;93m";
        public static final String VIOLET = "\u001B[38;5;129m";
        public static final String ORCHID = "\u001B[38;5;170m";

        public static final String BROWN = "\u001B[38;5;94m";
        public static final String GREY = "\u001B[38;5;244m";
        public static final String LIGHT_GREY = "\u001B[38;5;250m";
        public static final String DARK_GREY = "\u001B[38;5;238m";

        public static final String WHITE = "\u001B[38;5;15m";
        public static final String BLACK = "\u001B[38;5;0m";
    }

    public static final class Background {
        public static final String BLACK = "\u001B[40m";
        public static final String RED = "\u001B[41m";
        public static final String GREEN = "\u001B[42m";
        public static final String YELLOW = "\u001B[43m";
        public static final String BLUE = "\u001B[44m";
        public static final String PURPLE = "\u001B[45m";
        public static final String CYAN = "\u001B[46m";
        public static final String WHITE = "\u001B[47m";
    }

    public static final String RESET = "\u001B[0m";
}
