package dev.bibikvlad.mastermind.model.enums;

import dev.bibikvlad.utils.StringConstants.ConsoleColors;

public enum MastermindColors {
    RED(0, "Red", 'r', ConsoleColors.BrightForeground.RED),
    GREEN(1, "Green", 'g', ConsoleColors.BrightForeground.GREEN),
    YELLOW(2, "Yellow", 'y', ConsoleColors.BrightForeground.YELLOW),
    BLUE(3, "Blue", 'b', ConsoleColors.BrightForeground.BLUE),
    PURPLE(4, "Purple", 'p', ConsoleColors.BrightForeground.PURPLE),
    WHITE(5, "White", 'w', ConsoleColors.BrightForeground.WHITE);

    private final int colorIndex;
    private final String colorName;
    private final char symbol;
    private final String consoleColorCode;

    MastermindColors(int colorIndex, String colorName, char symbol, String consoleColorCode) {
        this.colorIndex = colorIndex;
        this.colorName = colorName;
        this.symbol = symbol;
        this.consoleColorCode = consoleColorCode;
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

    public String getConsoleColorCode() {
        return consoleColorCode;
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
            if (mastermindColor.symbol == symbol) {
                return mastermindColor;
            }
        }

        throw new IllegalArgumentException("Invalid color symbol");
    }
}
