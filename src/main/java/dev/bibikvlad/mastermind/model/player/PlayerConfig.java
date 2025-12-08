package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public class PlayerConfig {
    private final LocaleType locale;
    private final LogoColorsBundle logoColorsBundle;

    public PlayerConfig(LocaleType locale, LogoColorsBundle logoColorsBundle) {
        this.locale = locale;
        this.logoColorsBundle = logoColorsBundle;
    }

    public LocaleType getLocale() {
        return locale;
    }

    public LogoColorsBundle getLogoColorsBundle() {
        return logoColorsBundle;
    }

    public PlayerConfig withLocale(LocaleType localeType) {
        return new PlayerConfig(localeType, logoColorsBundle);
    }
}
