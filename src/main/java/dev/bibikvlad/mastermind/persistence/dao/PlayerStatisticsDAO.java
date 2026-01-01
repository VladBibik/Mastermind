package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.values.Time;

import java.util.List;

public interface PlayerStatisticsDAO {
    public List<Time> getTotalPlayTime();

    public Time getTotalPlayTimeByPlayerId(long playerId);
}
