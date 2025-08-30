package dev.bibikvlad.mastermind.model.enums;

import java.util.*;
import java.util.stream.Collectors;

public enum ConsoleColor {
    BLACK(1, "\u001B[30m", "Black", Category.FOREGROUND),
    RED(2, "\u001B[31m", "Red", Category.FOREGROUND),
    GREEN(3, "\u001B[32m", "Green", Category.FOREGROUND),
    YELLOW(4, "\u001B[33m", "Yellow", Category.FOREGROUND),
    BLUE(5, "\u001B[34m", "Blue", Category.FOREGROUND),
    PURPLE(6, "\u001B[35m", "Purple", Category.FOREGROUND),
    CYAN(7, "\u001B[36m", "Cyan", Category.FOREGROUND),
    WHITE(8, "\u001B[37m", "White", Category.FOREGROUND),

    BRIGHT_BLACK(9, "\u001B[90m", "Bright Black", Category.FOREGROUND),
    BRIGHT_RED(10, "\u001B[91m", "Bright Red", Category.FOREGROUND),
    BRIGHT_GREEN(11, "\u001B[92m", "Bright Green", Category.FOREGROUND),
    BRIGHT_YELLOW(12, "\u001B[93m", "Bright Yellow", Category.FOREGROUND),
    BRIGHT_BLUE(13, "\u001B[94m", "Bright Blue", Category.FOREGROUND),
    BRIGHT_PURPLE(14, "\u001B[95m", "Bright Purple", Category.FOREGROUND),
    BRIGHT_CYAN(15, "\u001B[96m", "Bright Cyan", Category.FOREGROUND),
    BRIGHT_WHITE(16, "\u001B[97m", "Bright White", Category.FOREGROUND),
    ORANGE(17, "\u001B[38;5;208m", "Orange", Category.FOREGROUND),

    LIGHT_ORANGE(18, "\u001B[38;5;214m", "Light Orange", Category.FOREGROUND),
    DARK_ORANGE(19, "\u001B[38;5;166m", "Dark Orange", Category.FOREGROUND),
    LIGHT_BLUE(20, "\u001B[38;5;117m", "Light Blue", Category.FOREGROUND),
    SKY_BLUE(21, "\u001B[38;5;111m", "Sky Blue", Category.FOREGROUND),
    DARK_BLUE(22, "\u001B[38;5;19m", "Dark Blue", Category.FOREGROUND),
    TEAL(23, "\u001B[38;5;37m", "Teal", Category.FOREGROUND),
    EXTENDED_CYAN(24, "\u001B[38;5;51m", "Extended Cyan", Category.FOREGROUND),
    LIME(25, "\u001B[38;5;154m", "Lime", Category.FOREGROUND),
    EXTENDED_GREEN(26, "\u001B[38;5;40m", "Extended Green", Category.FOREGROUND),
    DARK_GREEN(27, "\u001B[38;5;22m", "Dark Green", Category.FOREGROUND),
    EXTENDED_YELLOW(28, "\u001B[38;5;226m", "Extended Yellow", Category.FOREGROUND),
    GOLD(29, "\u001B[38;5;220m", "Gold", Category.FOREGROUND),
    PINK(30, "\u001B[38;5;213m", "Pink", Category.FOREGROUND),
    HOT_PINK(31, "\u001B[38;5;205m", "Hot Pink", Category.FOREGROUND),
    MAGENTA(32, "\u001B[38;5;201m", "Magenta", Category.FOREGROUND),
    EXTENDED_PURPLE(33, "\u001B[38;5;93m", "Extended Purple", Category.FOREGROUND),
    VIOLET(34, "\u001B[38;5;129m", "Violet", Category.FOREGROUND),
    ORCHID(35, "\u001B[38;5;170m", "Orchid", Category.FOREGROUND),
    BROWN(36, "\u001B[38;5;94m", "Brown", Category.FOREGROUND),
    GREY(37, "\u001B[38;5;244m", "Grey", Category.FOREGROUND),
    LIGHT_GREY(38, "\u001B[38;5;250m", "Light Gray", Category.FOREGROUND),
    DARK_GREY(39, "\u001B[38;5;238m", "Dark Gray", Category.FOREGROUND),

    BACKGROUND_BLACK(1, "\u001B[40m", "Background Black", Category.BACKGROUND),
    BACKGROUND_RED(2, "\u001B[41m", "Background Red", Category.BACKGROUND),
    BACKGROUND_GREEN(3, "\u001B[42m", "Background Green", Category.BACKGROUND),
    BACKGROUND_YELLOW(4, "\u001B[43m", "Background Yellow", Category.BACKGROUND),
    BACKGROUND_BLUE(5, "\u001B[44m", "Background Blue", Category.BACKGROUND),
    BACKGROUND_PURPLE(6, "\u001B[45m", "Background Purple", Category.BACKGROUND),
    BACKGROUND_CYAN(7, "\u001B[46m", "Background Cyan", Category.BACKGROUND),
    BACKGROUND_WHITE(8, "\u001B[47m", "Background White", Category.BACKGROUND),

    RESET(0, "\u001B[0m", "Reset", Category.FOREGROUND);

    private final int index;
    private final String code;
    private final String displayName;
    private final Category category;

    private static final Map<Integer, ConsoleColor> BY_ID_FOREGROUND = new HashMap<>();
    private static final Map<Integer, ConsoleColor> BY_ID_BACKGROUND = new HashMap<>();
    private static final Map<String, ConsoleColor> BY_DISPLAY_NAME = new HashMap<>();
    private static final Map<Category, List<ConsoleColor>> BY_CATEGORY =
            Arrays.stream(values()).collect(Collectors.groupingBy(ConsoleColor::getCategory));

    static {
        for (ConsoleColor consoleColor : ConsoleColor.values()) {
            BY_DISPLAY_NAME.put(consoleColor.getDisplayName(), consoleColor);
        }

        for (ConsoleColor consoleColor : Arrays.stream(ConsoleColor.values())
                .filter(color -> color.getCategory() == Category.FOREGROUND)
                .collect(Collectors.toSet())) {
            BY_ID_FOREGROUND.put(consoleColor.getIndex(), consoleColor);
        }

        for (ConsoleColor consoleColor : Arrays.stream(ConsoleColor.values())
                .filter(color -> color.getCategory() == Category.BACKGROUND)
                .collect(Collectors.toSet())) {
            BY_ID_BACKGROUND.put(consoleColor.getIndex(), consoleColor);
        }
    }

    ConsoleColor(int id, String code, String displayName, Category category) {
        this.index = id;
        this.code = code;
        this.displayName = displayName;
        this.category = category;
    }

    public int getIndex() {
        return index;
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

    public static ConsoleColor getForegroundColorByIndex(Integer index) {
        return Optional.ofNullable(BY_ID_FOREGROUND.get(index)).orElseThrow(
                () -> new IllegalArgumentException("No foreground color found for index " + index));
    }

    public static ConsoleColor getBackgroundColorByIndex(Integer index) {
        return Optional.ofNullable(BY_ID_BACKGROUND.get(index)).orElseThrow(
                () -> new IllegalArgumentException("No background color found for index " + index));
    }

    public static ConsoleColor getByDisplayName(String displayName) {
        return Optional.ofNullable(BY_DISPLAY_NAME.get(displayName)).orElseThrow(
                () -> new IllegalArgumentException("Invalid Display Name: " + displayName));
    }

    public static List<ConsoleColor> getByCategory(Category category) {
        return BY_CATEGORY.get(category);
    }

    public static List<ConsoleColor> getForegroundColors() {
        return BY_CATEGORY.get(Category.FOREGROUND);
    }

    public static List<ConsoleColor> getBackgroundColors() {
        return BY_CATEGORY.get(Category.BACKGROUND);
    }

    public enum Category {FOREGROUND, BACKGROUND}
}
