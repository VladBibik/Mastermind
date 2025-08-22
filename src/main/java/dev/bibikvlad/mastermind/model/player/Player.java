package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.time.LocalDateTime;

public class Player {
    private String playerName;
    private LocalDateTime creationDate;
    private PlayerConfig playerConfig;

    //TODO: Analyse if there is a better way to fix constructor issue
    public Player(String playerName, PlayerConfig playerConfig) {
        this.playerName = playerName;
        this.playerConfig = playerConfig;
    }

    public Player(String playerName, LocalDateTime creationDate, PlayerConfig playerConfig) {
        this.playerName = playerName;
        this.creationDate = creationDate;
        this.playerConfig = playerConfig;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
        return "Player: \n"
                + "Player ID: " + playerConfig.playerId
                + ", Player Name = " + playerName
                + ", Creation Date = " + SQLiteTimestampFormatter.format(creationDate)
                + "\n    configurations:"
                + "\n        Selected Language: " + playerConfig.getLocale().getLanguageName()
                + "\n";
    }
}
