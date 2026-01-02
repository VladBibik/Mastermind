package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.values.Time;

public record PlayerStatistics(long gameCount, long winCount, double winPercentage, Time totalPlayTime,
                               Time averageGameDuration, Time fastestWinTime, int minTurnsWin) {
}
