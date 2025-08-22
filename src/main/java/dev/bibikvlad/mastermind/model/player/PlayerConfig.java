package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;

public class PlayerConfig {
    int playerId;
    LocaleType locale;

    public PlayerConfig(LocaleType locale) {
        this.locale = locale;
    }

    public PlayerConfig(int playerId, LocaleType locale) {
        this.playerId = playerId;
        this.locale = locale;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public LocaleType getLocale() {
        return locale;
    }

    public void setLocale(LocaleType locale) {
        this.locale = locale;
    }
}
