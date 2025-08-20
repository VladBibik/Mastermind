package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;

import java.time.LocalDateTime;

public class Player {
    private String username;
    private LocaleType locale;
    private LocalDateTime creationDate;

    public Player(String username, LocaleType locale, LocalDateTime creationDate) {
        this.username = username;
        this.locale = locale;
        this.creationDate = creationDate;
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
        return "Player: " + "username = " + username + ", locale = " + locale + ", creationDate = " + creationDate;
    }
}
