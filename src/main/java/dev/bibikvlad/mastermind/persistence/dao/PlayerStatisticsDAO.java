package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.values.Time;

public interface PlayerStatisticsDAO {
    Time getTotalPlayTime(long playerId);
}
