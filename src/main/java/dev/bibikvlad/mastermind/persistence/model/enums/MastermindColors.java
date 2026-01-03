package dev.bibikvlad.mastermind.persistence.model.enums;

import java.util.HashMap;
import java.util.Optional;

public enum MastermindColors {
    RED(0, "Red", 'r'),
    GREEN(1, "Green", 'g'),
    YELLOW(2, "Yellow", 'y'),
    BLUE(3, "Blue", 'b'),
    PURPLE(4, "Purple", 'p'),
    WHITE(5, "White", 'w');

    private final int colorIndex;
    private final String colorName;
    private final char symbol;

    private static final HashMap<Integer, MastermindColors> BY_COLOR_INDEX = new HashMap<>();
    private static final HashMap<Character, MastermindColors> BY_SYMBOL = new HashMap<>();

    static {
        for (MastermindColors mastermindColors : MastermindColors.values()) {
            BY_COLOR_INDEX.put(mastermindColors.colorIndex, mastermindColors);
            BY_SYMBOL.put(mastermindColors.symbol, mastermindColors);
        }
    }

    MastermindColors(int colorIndex, String colorName, char symbol) {
        this.colorIndex = colorIndex;
        this.colorName = colorName;
        this.symbol = symbol;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public String getColorName() {
        return colorName;
    }

    public char getSymbol() {
        return symbol;
    }

    public static MastermindColors fromColorIndex(int colorIndex) {
        return Optional.ofNullable(BY_COLOR_INDEX.get(colorIndex))
                .orElseThrow(() -> new IllegalArgumentException("Invalid color index: " + colorIndex));
    }

    public static MastermindColors fromColorSymbol(char symbol) {
        return Optional.ofNullable(BY_SYMBOL.get(Character.toLowerCase(symbol)))
                .orElseThrow(() -> new IllegalArgumentException("Invalid color symbol: " + symbol));
    }
}
