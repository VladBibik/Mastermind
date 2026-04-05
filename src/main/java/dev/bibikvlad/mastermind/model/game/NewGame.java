package dev.bibikvlad.mastermind.model.game;

import dev.bibikvlad.mastermind.game.data.GameData;

public record NewGame(long playerId, GameData gameData) {
}
