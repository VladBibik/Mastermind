package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public record PlayerConfig(LocalizationType localizationType, LogoColorsBundle logoColorsBundle) {

    public PlayerConfig withLocalization(LocalizationType localizationType) {
        return new PlayerConfig(localizationType, logoColorsBundle);
    }

    public PlayerConfig withLogoColorsBundle(LogoColorsBundle logoColorsBundle) {
        return new PlayerConfig(localizationType, logoColorsBundle);
    }
}
