package dev.bibikvlad.mastermind.model.game;

import dev.bibikvlad.mastermind.game.data.GameData;

public class NewGame {
    private final long playerId;
    private final GameData gameData;

    public NewGame(long playerId, GameData gameData) {
        this.playerId = playerId;
        this.gameData = gameData;
    }

    public long getPlayerId() {
        return playerId;
    }

    public GameData getGameData() {
        return gameData;
    }
}
