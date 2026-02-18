package dev.bibikvlad.mastermind.persistence.player.model;

import dev.bibikvlad.mastermind.values.Time;

public record PlayerStatistics(long gameCount, long winCount, double winPercentage, Time totalPlayTime,
                               long averageGameDuration, long fastestWinTime, int minTurnsWin) {
}
