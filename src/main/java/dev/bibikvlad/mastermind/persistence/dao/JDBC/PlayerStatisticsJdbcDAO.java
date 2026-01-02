package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.persistence.dao.PlayerStatisticsDAO;
import dev.bibikvlad.mastermind.values.Time;
import dev.bibikvlad.utils.formatters.MillisecondsToTimeFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStatisticsJdbcDAO implements PlayerStatisticsDAO {
    private final Connection connection;

    public PlayerStatisticsJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Time getTotalPlayTime(long playerId) {
        String getTotalPlayTimeByPlayerIdQuery = """
                SELECT COALESCE(SUM(duration_milliseconds)) AS total_playtime
                FROM games
                WHERE PLAYER_ID = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getTotalPlayTimeByPlayerIdQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            long durationMilliseconds = resultSet.getLong("total_playtime");

            return MillisecondsToTimeFormatter.format(durationMilliseconds);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch total play time for a player with ID: " + playerId,
                    exception);
        }
    }

    @Override
    public double getWinPercentage(long playerId) {
        String getWinPercentageQuery = """
                SELECT COUNT(*) FILTER (WHERE result = 'WIN') * 100.0 / COUNT(*) AS win_percentage
                FROM games
                WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getWinPercentageQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getDouble("win_percentage");
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch win percentage for a player with ID: " + playerId,
                    exception);
        }
    }

    @Override
    public Time getAverageGameDuration(long playerId) {
        String getAverageGameDurationQuery = """
                SELECT AVG(duration_milliseconds) AS average_game_duration
                FROM games
                WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getAverageGameDurationQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            long durationMilliseconds = resultSet.getLong("average_game_duration");

            return MillisecondsToTimeFormatter.format(durationMilliseconds);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch average game duration for a player with ID: " + playerId,
                    exception);
        }
    }

    @Override
    public Time getFastestWinTime(long playerId) {
        return null;
    }

    @Override
    public long getWinCount(long playerId) {
        return 0;
    }
}
