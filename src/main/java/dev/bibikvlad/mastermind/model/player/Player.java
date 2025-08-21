package dev.bibikvlad.mastermind.model.player;

import java.time.LocalDateTime;

public class Player {
    private String username;
    private LocalDateTime creationDate;
    private PlayerConfig playerConfig;

    public Player(String username, LocalDateTime creationDate, PlayerConfig playerConfig) {
        this.username = username;
        this.creationDate = creationDate;
        this.playerConfig = playerConfig;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "Player: " + "username = " + username + ", creationDate = " + creationDate;
    }
}
