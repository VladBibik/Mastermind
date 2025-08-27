package dev.bibikvlad.mastermind.model.enums;

public enum ConsoleColor {
    BLACK("\u001B[30m", "Black", Category.FOREGROUND),
    RED("\u001B[31m", "Red", Category.FOREGROUND),
    GREEN("\u001B[32m", "Green", Category.FOREGROUND),
    YELLOW("\u001B[33m", "Yellow", Category.FOREGROUND),
    BLUE("\u001B[34m", "Blue", Category.FOREGROUND),
    PURPLE("\u001B[35m", "Purple", Category.FOREGROUND),
    CYAN("\u001B[36m", "Cyan", Category.FOREGROUND),
    WHITE("\u001B[37m", "White", Category.FOREGROUND),

    BRIGHT_BLACK("\u001B[90m", "Bright Black", Category.FOREGROUND),
    BRIGHT_RED("\u001B[91m", "Bright Red", Category.FOREGROUND),
    BRIGHT_GREEN("\u001B[92m", "Bright Green", Category.FOREGROUND),
    BRIGHT_YELLOW("\u001B[93m", "Bright Yellow", Category.FOREGROUND),
    BRIGHT_BLUE("\u001B[94m", "Bright Blue", Category.FOREGROUND),
    BRIGHT_PURPLE("\u001B[95m", "Bright Purple", Category.FOREGROUND),
    BRIGHT_CYAN("\u001B[96m", "Bright Cyan", Category.FOREGROUND),
    BRIGHT_WHITE("\u001B[97m", "Bright White", Category.FOREGROUND),
    ORANGE("\u001B[38;5;208m", "Orange", Category.FOREGROUND),

    LIGHT_ORANGE("\u001B[38;5;214m", "Light Orange", Category.FOREGROUND),
    DARK_ORANGE("\u001B[38;5;166m", "Dark Orange", Category.FOREGROUND),
    LIGHT_BLUE("\u001B[38;5;117m", "Light Blue", Category.FOREGROUND),
    SKY_BLUE("\u001B[38;5;111m", "Sky Blue", Category.FOREGROUND),
    DARK_BLUE("\u001B[38;5;19m", "Dark Blue", Category.FOREGROUND),
    TEAL("\u001B[38;5;37m", "Teal", Category.FOREGROUND),
    EXTENDED_CYAN("\u001B[38;5;51m", "Extended Cyan", Category.FOREGROUND),
    LIME("\u001B[38;5;154m", "Lime", Category.FOREGROUND),
    EXTENDED_GREEN("\u001B[38;5;40m", "Extended Green", Category.FOREGROUND),
    DARK_GREEN("\u001B[38;5;22m", "Dark Green", Category.FOREGROUND),
    EXTENDED_YELLOW("\u001B[38;5;226m", "Extended Yellow", Category.FOREGROUND),
    GOLD("\u001B[38;5;220m", "Gold", Category.FOREGROUND),
    PINK("\u001B[38;5;213m", "Pink", Category.FOREGROUND),
    HOT_PINK("\u001B[38;5;205m", "Hot Pink", Category.FOREGROUND),
    MAGENTA("\u001B[38;5;201m", "Magenta", Category.FOREGROUND),
    EXTENDED_PURPLE("\u001B[38;5;93m", "Extended Purple", Category.FOREGROUND),
    VIOLET("\u001B[38;5;129m", "Violet", Category.FOREGROUND),
    ORCHID("\u001B[38;5;170m", "Orchid", Category.FOREGROUND),
    BROWN("\u001B[38;5;94m", "Brown", Category.FOREGROUND),
    GREY("\u001B[38;5;244m", "Grey", Category.FOREGROUND),
    LIGHT_GREY("\u001B[38;5;250m", "Light Green", Category.FOREGROUND),
    DARK_GREY("\u001B[38;5;238m", "Dark Green", Category.FOREGROUND),

    BACKGROUND_BLACK("\u001B[40m", "Background Black", Category.BACKGROUND),
    BACKGROUND_RED("\u001B[41m", "Background Red", Category.BACKGROUND),
    BACKGROUND_GREEN("\u001B[42m", "Background Green", Category.BACKGROUND),
    BACKGROUND_YELLOW("\u001B[43m", "Background Yellow", Category.BACKGROUND),
    BACKGROUND_BLUE("\u001B[44m", "Background Blue", Category.BACKGROUND),
    BACKGROUND_PURPLE("\u001B[45m", "Background Purple", Category.BACKGROUND),
    BACKGROUND_CYAN("\u001B[46m", "Background Cyan", Category.BACKGROUND),
    BACKGROUND_WHITE("\u001B[47m", "Background White", Category.BACKGROUND),

    RESET("\u001B[0m", "Reset", Category.FOREGROUND);

    private final String code;
    private final String displayName;
    private final Category category;

    ConsoleColor(String code, String displayName, Category category) {
        this.code = code;
        this.displayName = displayName;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Category getCategory() {
        return category;
    }

    public enum Category {FOREGROUND, BACKGROUND}
}
