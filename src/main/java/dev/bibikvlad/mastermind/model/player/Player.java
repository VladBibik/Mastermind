package dev.bibikvlad.mastermind.model.player;

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

    @Override
    public String toString() {
        return "Player: \n"
                + "Player ID: " + id
                + ", Player Name = " + playerName
                + ", Creation Date = " + SQLiteTimestampFormatter.format(creationDate)
                + "\n    configurations:"
                + "\n        Selected Language: " + playerConfig.getLocale().getLanguageName()
                + "\n";
    }
}
