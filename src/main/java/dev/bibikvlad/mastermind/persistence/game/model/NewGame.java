package dev.bibikvlad.mastermind.persistence.game.model;

import dev.bibikvlad.mastermind.game.data.GameData;

public record NewGame(long playerId, GameData gameData) {
}
