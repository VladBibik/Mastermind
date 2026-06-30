package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

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
}
