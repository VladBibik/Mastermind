package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.persistence.dao.PlayerStatisticsDAO;
import dev.bibikvlad.mastermind.values.Time;

import java.sql.Connection;
import java.util.List;

public class PlayerStatisticsJdbcDAO implements PlayerStatisticsDAO {
    private final Connection connection;

    public PlayerStatisticsJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Time> getTotalPlayTime() {
        return List.of();
    }

    @Override
    public Time getTotalPlayTimeByPlayerId(long playerId) {
        return null;
    }
}
