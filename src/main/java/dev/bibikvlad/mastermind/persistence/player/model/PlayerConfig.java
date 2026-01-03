package dev.bibikvlad.mastermind.persistence.player.model;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public record PlayerConfig(LocaleType locale, LogoColorsBundle logoColorsBundle) {

    public PlayerConfig withLocale(LocaleType localeType) {
        return new PlayerConfig(localeType, logoColorsBundle);
    }
}
