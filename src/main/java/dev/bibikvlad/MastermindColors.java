package dev.bibikvlad;

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
}
