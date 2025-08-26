package dev.bibikvlad.mastermind.model.enums.colors;

public enum BrightForegroundColor {
    BLACK("\u001B[90m"),
    RED("\u001B[91m"),
    GREEN("\u001B[92m"),
    YELLOW("\u001B[93m"),
    BLUE("\u001B[94m"),
    PURPLE("\u001B[95m"),
    CYAN("\u001B[96m"),
    WHITE("\u001B[97m");

    private final String code;

    BrightForegroundColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
