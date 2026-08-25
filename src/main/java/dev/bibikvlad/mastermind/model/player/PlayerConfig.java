package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public record PlayerConfig(LocalizationType locale, LogoColorsBundle logoColorsBundle) {

    public PlayerConfig withLocale(LocalizationType localizationType) {
        return new PlayerConfig(localizationType, logoColorsBundle);
    }

    public PlayerConfig withLogoColorsBundle(LogoColorsBundle logoColorsBundle) {
        return new PlayerConfig(locale, logoColorsBundle);
    }
}
