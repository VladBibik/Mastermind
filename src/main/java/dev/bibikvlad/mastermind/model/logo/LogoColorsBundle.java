package dev.bibikvlad.mastermind.model.logo;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

public class LogoColorsBundle {
    private final ConsoleColor logoBorderColor;
    private final ConsoleColor logoMainColor;
    private final ConsoleColor logoAccentColor;
    private final ConsoleColor logoBackgroundColor;

    public LogoColorsBundle(ConsoleColor logoBorderColor, ConsoleColor logoMainColor, ConsoleColor logoAccentColor,
                            ConsoleColor logoBackgroundColor) {
        this.logoBorderColor = logoBorderColor;
        this.logoMainColor = logoMainColor;
        this.logoAccentColor = logoAccentColor;
        this.logoBackgroundColor = logoBackgroundColor;
    }

    public ConsoleColor getLogoBorderColor() {
        return logoBorderColor;
    }

    public ConsoleColor getLogoMainColor() {
        return logoMainColor;
    }

    public ConsoleColor getLogoAccentColor() {
        return logoAccentColor;
    }

    public ConsoleColor getLogoBackgroundColor() {
        return logoBackgroundColor;
    }
}
