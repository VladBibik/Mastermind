package dev.bibikvlad.mastermind.model.enums;

import java.util.HashMap;

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

    private static final HashMap<Integer, MastermindColors> BY_COLOR_INDEX = new HashMap<Integer, MastermindColors>();
    private static final HashMap<Character, MastermindColors> BY_SYMBOL = new HashMap<Character, MastermindColors>();

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
        MastermindColors mastermindColors = BY_COLOR_INDEX.get(colorIndex);

        if (mastermindColors == null) {
            throw new IllegalStateException("MastermindColors not found for color index: " + colorIndex);
        }

        return mastermindColors;
    }

    public static MastermindColors fromColorSymbol(char symbol) {
        MastermindColors mastermindColors = BY_SYMBOL.get(symbol);

        if (mastermindColors == null) {
            throw new IllegalStateException("MastermindColors not found for color symbol: " + symbol);
        }

        return mastermindColors;
    }
}
