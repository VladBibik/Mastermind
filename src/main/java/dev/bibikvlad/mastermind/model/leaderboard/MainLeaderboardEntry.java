package dev.bibikvlad.mastermind.model.leaderboard;

import dev.bibikvlad.mastermind.values.Time;

public record MainLeaderboardEntry(String playerName, int numberOfTurns, Time gameDuration) {
}
