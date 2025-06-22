package dev.bibikvlad.mastermind.model.enums;

public enum MastermindColors {
    RED(0, "Red"),
    GREEN(1, "Green"),
    YELLOW(2, "Yellow"),
    BLUE(3, "Blue"),
    PURPLE(4, "Purple"),
    WHITE(5, "White");

    private final int colorIndex;
    private final String colorName;

    MastermindColors(int colorIndex, String colorName) {
        this.colorIndex = colorIndex;
        this.colorName = colorName;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public String getColorName() {
        return colorName;
    }

    public static MastermindColors fromColorIndex(int colorIndex) {
        for (MastermindColors mastermindColor : MastermindColors.values()) {
            if (mastermindColor.colorIndex == colorIndex) {
                return mastermindColor;
            }
        }

        throw new IllegalArgumentException("Invalid color index");
    }
}
