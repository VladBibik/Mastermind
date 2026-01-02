package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.values.Time;

public record PlayerStatistics(long gameCount, long winCount, double winPercentage, Time averageGameDuration,
                               Time fastestWinTime, int minTurnsWin) {
}
