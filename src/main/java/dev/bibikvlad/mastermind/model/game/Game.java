package dev.bibikvlad.mastermind.model.game;

import dev.bibikvlad.mastermind.game.data.GameData;

import java.time.LocalDateTime;

public class Game {
    private final long gameId;
    private final long playerId;
    private final GameData gameData;
    private final LocalDateTime startedAt;

    public Game(long gameId, long playerId, GameData gameData, LocalDateTime startedAt) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.gameData = gameData;
        this.startedAt = startedAt;
    }

    public long getGameId() {
        return gameId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public GameData getGameData() {
        return gameData;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }
}
