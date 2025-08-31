package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

public class PlayerConfig {
    private final LocaleType locale;
    private final ConsoleColor borderColor;
    private final ConsoleColor mainColor;
    private final ConsoleColor accentColor;
    private final ConsoleColor backgroundColor;

    public PlayerConfig(LocaleType locale, ConsoleColor borderColor, ConsoleColor mainColor, ConsoleColor accentColor, ConsoleColor backgroundColor) {
        this.locale = locale;
        this.borderColor = borderColor;
        this.mainColor = mainColor;
        this.accentColor = accentColor;
        this.backgroundColor = backgroundColor;
    }

    public LocaleType getLocale() {
        return locale;
    }

    public ConsoleColor getBorderColor() {
        return borderColor;
    }

    public ConsoleColor getMainColor() {
        return mainColor;
    }

    public ConsoleColor getAccentColor() {
        return accentColor;
    }

    public ConsoleColor getBackgroundColor() {
        return backgroundColor;
    }
}
