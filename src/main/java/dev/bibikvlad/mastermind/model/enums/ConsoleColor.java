package dev.bibikvlad.mastermind.model.enums;

public enum ConsoleColor {
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    BRIGHT_BLACK("\u001B[90m"),
    BRIGHT_RED("\u001B[91m"),
    BRIGHT_GREEN("\u001B[92m"),
    BRIGHT_YELLOW("\u001B[93m"),
    BRIGHT_BLUE("\u001B[94m"),
    BRIGHT_PURPLE("\u001B[95m"),
    BRIGHT_CYAN("\u001B[96m"),
    BRIGHT_WHITE("\u001B[97m"),
    ORANGE("\u001B[38;5;208m"),

    LIGHT_ORANGE("\u001B[38;5;214m"),
    DARK_ORANGE("\u001B[38;5;166m"),
    LIGHT_BLUE("\u001B[38;5;117m"),
    SKY_BLUE("\u001B[38;5;111m"),
    DARK_BLUE("\u001B[38;5;19m"),
    TEAL("\u001B[38;5;37m"),
    EXTENDED_CYAN("\u001B[38;5;51m"),
    LIME("\u001B[38;5;154m"),
    EXTENDED_GREEN("\u001B[38;5;40m"),
    DARK_GREEN("\u001B[38;5;22m"),
    EXTENDED_YELLOW("\u001B[38;5;226m"),
    GOLD("\u001B[38;5;220m"),
    PINK("\u001B[38;5;213m"),
    HOT_PINK("\u001B[38;5;205m"),
    MAGENTA("\u001B[38;5;201m"),
    EXTENDED_PURPLE("\u001B[38;5;93m"),
    VIOLET("\u001B[38;5;129m"),
    ORCHID("\u001B[38;5;170m"),
    BROWN("\u001B[38;5;94m"),
    GREY("\u001B[38;5;244m"),
    LIGHT_GREY("\u001B[38;5;250m"),
    DARK_GREY("\u001B[38;5;238m"),

    BACKGROUND_BLACK("\u001B[40m"),
    BACKGROUND_RED("\u001B[41m"),
    BACKGROUND_GREEN("\u001B[42m"),
    BACKGROUND_YELLOW("\u001B[43m"),
    BACKGROUND_BLUE("\u001B[44m"),
    BACKGROUND_PURPLE("\u001B[45m"),
    BACKGROUND_CYAN("\u001B[46m"),
    BACKGROUND_WHITE("\u001B[47m"),

    RESET("\u001B[0m");

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
