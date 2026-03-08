package dev.bibikvlad.mastermind.persistence.player.model;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.time.LocalDateTime;

public class Player {
    private Long id;
    private final String playerName;
    private final LocalDateTime creationDate;
    private final PlayerConfig playerConfig;

    public Player(String playerName, PlayerConfig playerConfig) {
        this.playerName = playerName;
        this.creationDate = LocalDateTime.now();
        this.playerConfig = playerConfig;
    }

    public Player(Long id, String playerName, LocalDateTime creationDate, PlayerConfig playerConfig) {
        this.id = id;
        this.playerName = playerName;
        this.creationDate = creationDate;
        this.playerConfig = playerConfig;
    }

    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public Player withLocale(LocaleType localeType) {
        PlayerConfig playerConfig = getPlayerConfig().withLocale(localeType);

        return new Player(id, playerName, creationDate, playerConfig);
    }

    public Player withLogoColorsBundle(LogoColorsBundle logoColorsBundle) {
        PlayerConfig playerConfig = getPlayerConfig().withLogoColorsBundle(logoColorsBundle);

        return new Player(id, playerName, creationDate, playerConfig);
    }

    //TODO: Remove it after test phase is done!
    @Override
    public String toString() {
        return "Player: \n"
                + "Player ID: " + id
                + ", Player Name = " + playerName
                + ", Creation Date = " + SQLiteTimestampFormatter.format(creationDate)
                + "\n    configurations:"
                + "\n        Selected Language: " + playerConfig.locale().getNativeDisplayName()
                + "\n        Selected Logo Border Color: " + playerConfig.logoColorsBundle()
                .logoBorderColor().getDisplayName()
                + "\n        Selected Logo Main Color: " + playerConfig.logoColorsBundle()
                .logoMainColor().getDisplayName()
                + "\n        Selected Logo Accent Color: " + playerConfig.logoColorsBundle()
                .logoAccentColor().getDisplayName()
                + "\n        Selected Logo Background Color: " + playerConfig.logoColorsBundle()
                .logoBackgroundColor().getDisplayName()
                + "\n";
    }
}
