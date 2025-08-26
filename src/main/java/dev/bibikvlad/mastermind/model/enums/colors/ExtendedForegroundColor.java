package dev.bibikvlad.mastermind.model.enums.colors;

public enum ExtendedForegroundColor {
    ORANGE("\u001B[38;5;208m"),
    LIGHT_ORANGE("\u001B[38;5;214m"),
    DARK_ORANGE("\u001B[38;5;166m"),
    LIGHT_BLUE("\u001B[38;5;117m"),
    SKY_BLUE("\u001B[38;5;111m"),
    DARK_BLUE("\u001B[38;5;19m"),
    TEAL("\u001B[38;5;37m"),
    CYAN("\u001B[38;5;51m"),
    LIME("\u001B[38;5;154m"),
    GREEN("\u001B[38;5;40m"),
    DARK_GREEN("\u001B[38;5;22m"),
    YELLOW("\u001B[38;5;226m"),
    GOLD("\u001B[38;5;220m"),
    PINK("\u001B[38;5;213m"),
    HOT_PINK("\u001B[38;5;205m"),
    MAGENTA("\u001B[38;5;201m"),
    PURPLE("\u001B[38;5;93m"),
    VIOLET("\u001B[38;5;129m"),
    ORCHID("\u001B[38;5;170m"),
    BROWN("\u001B[38;5;94m"),
    GREY("\u001B[38;5;244m"),
    LIGHT_GREY("\u001B[38;5;250m"),
    DARK_GREY("\u001B[38;5;238m");

    private final String code;

    ExtendedForegroundColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
