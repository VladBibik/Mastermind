package dev.bibikvlad.mastermind.persistence.game.model;

import dev.bibikvlad.mastermind.game.data.GameData;

import java.time.LocalDateTime;

public record Game(long gameId, long playerId, GameData gameData, LocalDateTime startedAt) {
}
