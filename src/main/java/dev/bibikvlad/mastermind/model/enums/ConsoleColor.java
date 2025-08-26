package dev.bibikvlad.mastermind.model.enums;

public enum ConsoleColor {
    BLACK("\u001B[30m", "Black"),
    RED("\u001B[31m", "Red"),
    GREEN("\u001B[32m", "Green"),
    YELLOW("\u001B[33m", "Yellow"),
    BLUE("\u001B[34m", "Blue"),
    PURPLE("\u001B[35m", "Purple"),
    CYAN("\u001B[36m", "Cyan"),
    WHITE("\u001B[37m", "White"),

    BRIGHT_BLACK("\u001B[90m", "Bright Black"),
    BRIGHT_RED("\u001B[91m", "Bright Red"),
    BRIGHT_GREEN("\u001B[92m", "Bright Green"),
    BRIGHT_YELLOW("\u001B[93m", "Bright Yellow"),
    BRIGHT_BLUE("\u001B[94m", "Bright Blue"),
    BRIGHT_PURPLE("\u001B[95m", "Bright Purple"),
    BRIGHT_CYAN("\u001B[96m", "Bright Cyan"),
    BRIGHT_WHITE("\u001B[97m", "Bright White"),
    ORANGE("\u001B[38;5;208m", "Orange"),

    LIGHT_ORANGE("\u001B[38;5;214m", "Light Orange"),
    DARK_ORANGE("\u001B[38;5;166m", "Dark Orange"),
    LIGHT_BLUE("\u001B[38;5;117m", "Light Blue"),
    SKY_BLUE("\u001B[38;5;111m", "Sky Blue"),
    DARK_BLUE("\u001B[38;5;19m", "Dark Blue"),
    TEAL("\u001B[38;5;37m", "Teal"),
    EXTENDED_CYAN("\u001B[38;5;51m", "Extended Cyan"),
    LIME("\u001B[38;5;154m", "Lime"),
    EXTENDED_GREEN("\u001B[38;5;40m", "Extended Green"),
    DARK_GREEN("\u001B[38;5;22m", "Dark Green"),
    EXTENDED_YELLOW("\u001B[38;5;226m", "Extended Yellow"),
    GOLD("\u001B[38;5;220m", "Gold"),
    PINK("\u001B[38;5;213m", "Pink"),
    HOT_PINK("\u001B[38;5;205m", "Hot Pink"),
    MAGENTA("\u001B[38;5;201m", "Magenta"),
    EXTENDED_PURPLE("\u001B[38;5;93m", "Extended Purple"),
    VIOLET("\u001B[38;5;129m", "Violet"),
    ORCHID("\u001B[38;5;170m", "Orchid"),
    BROWN("\u001B[38;5;94m", "Brown"),
    GREY("\u001B[38;5;244m", "Grey"),
    LIGHT_GREY("\u001B[38;5;250m", "Light Green"),
    DARK_GREY("\u001B[38;5;238m", "Dark Green"),

    BACKGROUND_BLACK("\u001B[40m", "Background Black"),
    BACKGROUND_RED("\u001B[41m", "Background Red"),
    BACKGROUND_GREEN("\u001B[42m", "Background Green"),
    BACKGROUND_YELLOW("\u001B[43m", "Background Yellow"),
    BACKGROUND_BLUE("\u001B[44m", "Background Blue"),
    BACKGROUND_PURPLE("\u001B[45m", "Background Purple"),
    BACKGROUND_CYAN("\u001B[46m", "Background Cyan"),
    BACKGROUND_WHITE("\u001B[47m", "Background White"),

    RESET("\u001B[0m", "Reset"),;

    private final String code;
    private final String displayName;

    ConsoleColor(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
