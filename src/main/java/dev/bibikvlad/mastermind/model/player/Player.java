package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;

import java.time.LocalDateTime;

public class Player {
    private String username;
    private LocaleType locale;
    private LocalDateTime creationDate;
    private PlayerConfig playerConfig;

    public Player(String username, LocaleType locale, LocalDateTime creationDate, PlayerConfig playerConfig) {
        this.username = username;
        this.locale = locale;
        this.creationDate = creationDate;
        this.playerConfig = playerConfig;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public void setPlayerConfig(PlayerConfig playerConfig) {
        this.playerConfig = playerConfig;
    }

    @Override
    public String toString() {
        return "Player: " + "username = " + username + ", locale = " + locale + ", creationDate = " + creationDate;
    }
}
