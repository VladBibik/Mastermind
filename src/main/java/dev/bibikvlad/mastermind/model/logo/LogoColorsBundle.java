package dev.bibikvlad.mastermind.model.logo;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

public record LogoColorsBundle(ConsoleColor logoBorderColor, ConsoleColor logoMainColor, ConsoleColor logoAccentColor,
                               ConsoleColor logoBackgroundColor) {

    public LogoColorsBundle withLogoBorderColor(ConsoleColor borderColor) {
        return new LogoColorsBundle(borderColor, logoMainColor, logoAccentColor, logoBackgroundColor);
    }

    public LogoColorsBundle withLogoMainColor(ConsoleColor mainColor) {
        return new LogoColorsBundle(logoBorderColor, mainColor, logoAccentColor, logoBackgroundColor);
    }

    public LogoColorsBundle withLogoAccentColor(ConsoleColor accentColor) {
        return new LogoColorsBundle(logoBorderColor, logoMainColor, accentColor, logoBackgroundColor);
    }

    public LogoColorsBundle withLogoBackgroundColor(ConsoleColor backgroundColor) {
        return new LogoColorsBundle(logoBorderColor, logoMainColor, logoAccentColor, backgroundColor);
    }
}
