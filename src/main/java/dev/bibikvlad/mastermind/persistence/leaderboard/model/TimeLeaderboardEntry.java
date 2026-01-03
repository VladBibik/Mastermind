package dev.bibikvlad.mastermind.persistence.leaderboard.model;

import dev.bibikvlad.mastermind.values.Time;

public record TimeLeaderboardEntry(String playerName, Time gameDuration) {
}
