package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;

public class PlayerConfig {
    private final LocaleType locale;

    public PlayerConfig(LocaleType locale) {
        this.locale = locale;
    }

    public LocaleType getLocale() {
        return locale;
    }
}
