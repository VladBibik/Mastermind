package dev.bibikvlad.mastermind.model.enums;

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
        for (MastermindColors mastermindColor : MastermindColors.values()) {
            if (mastermindColor.colorIndex == colorIndex) {
                return mastermindColor;
            }
        }

        throw new IllegalArgumentException("Invalid color index");
    }

    public static MastermindColors fromColorSymbol(char symbol) {
        for (MastermindColors mastermindColor : MastermindColors.values()) {
            if (mastermindColor.symbol == Character.toLowerCase(symbol)) {
                return mastermindColor;
            }
        }

        throw new IllegalArgumentException("Invalid color symbol");
    }
}
