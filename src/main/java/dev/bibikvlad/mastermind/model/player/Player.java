package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;

public class Player {
    private String username;
    private LocaleType locale;

    public Player(String username, LocaleType locale) {
        this.username = username;
        this.locale = locale;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocaleType getLocale() {
        return locale;
    }

    public void setLocale(LocaleType locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "Player:\n"
                + "username = " + username + "\n"
                + "locale = " + locale;
    }
}
