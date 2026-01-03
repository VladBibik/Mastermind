package dev.bibikvlad.mastermind.persistence.leaderboard.model;

import dev.bibikvlad.mastermind.values.Time;

public record MainLeaderboardEntry(String playerName, int numberOfTurns, Time gameDuration) {
}
