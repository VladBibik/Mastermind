package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

public class PlayerConfig {
    private final LocaleType locale;
    private final ConsoleColor logoBorderColor;
    private final ConsoleColor logoMainColor;
    private final ConsoleColor logoAccentColor;
    private final ConsoleColor logoBackgroundColor;

    public PlayerConfig(LocaleType locale, ConsoleColor logoBorderColor, ConsoleColor logoMainColor,
                        ConsoleColor logoAccentColor, ConsoleColor logoBackgroundColor) {
        this.locale = locale;
        this.logoBorderColor = logoBorderColor;
        this.logoMainColor = logoMainColor;
        this.logoAccentColor = logoAccentColor;
        this.logoBackgroundColor = logoBackgroundColor;
    }

    public LocaleType getLocale() {
        return locale;
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
