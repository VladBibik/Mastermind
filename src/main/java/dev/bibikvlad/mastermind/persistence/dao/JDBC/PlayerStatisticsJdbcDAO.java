package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.PlayerStatistics;
import dev.bibikvlad.mastermind.persistence.dao.PlayerStatisticsDAO;
import dev.bibikvlad.mastermind.persistence.mappers.player.PlayerStatisticsMapper;
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
    public PlayerStatistics getPlayerStatistics(int playerId) {
        String getPlayerStatisticsQuery = """
                SELECT
                    COUNT(*) AS game_count,
                    COUNT(*) FILTER (WHERE result = 'WIN') AS win_count,
                    CASE
                        WHEN COUNT(*) = 0 THEN 0
                        ELSE COUNT(*) FILTER (WHERE result = 'WIN') * 100.0 / COUNT(*)
                    END AS win_percentage,
                    COALESCE(SUM(duration_milliseconds), 0) AS total_playtime,
                    COALESCE(AVG(duration_milliseconds), 0) AS average_game_duration,
                    COALESCE(
                        MIN(duration_milliseconds) FILTER (WHERE result = 'WIN'),
                        0
                    ) AS fastest_win_time,
                    COALESCE(
                        MIN(number_of_turns) FILTER (WHERE result = 'WIN'),
                        0
                    ) AS min_turns_win
                FROM games
                WHERE player_id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getPlayerStatisticsQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return PlayerStatisticsMapper.map(resultSet);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch total play time for a player with ID: " + playerId,
                    exception);
        }
    }

    @Override
    public Time getTotalPlayTime(long playerId) {
        String getTotalPlayTimeQuery = """
                SELECT COALESCE(SUM(duration_milliseconds), 0) AS total_playtime
                FROM games
                WHERE PLAYER_ID = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getTotalPlayTimeQuery)) {
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
                SELECT CASE
                           WHEN COUNT(*) = 0 THEN 0
                           ELSE COUNT(*) FILTER (WHERE result = 'WIN') * 100.0 / COUNT(*)
                       END AS win_percentage
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
                SELECT COALESCE(AVG(duration_milliseconds), 0) AS average_game_duration
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
        String getFastestWinTimeQuery = """
                SELECT COALESCE(MIN(duration_milliseconds) FILTER (WHERE result = 'WIN'), 0) AS fastest_win_time
                FROM games
                WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getFastestWinTimeQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            long durationMilliseconds = resultSet.getLong("fastest_win_time");

            return MillisecondsToTimeFormatter.format(durationMilliseconds);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch fastest win time for a player with ID: " + playerId,
                    exception);
        }
    }

    @Override
    public long getWinCount(long playerId) {
        String getWinCountQuery = """
                SELECT COALESCE(COUNT(game_id), 0) AS win_count
                FROM games
                WHERE player_id = ? AND result = 'WIN';
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getWinCountQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getLong("win_count");
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch number of wins for a player with ID: " + playerId,
                    exception);
        }
    }

    @Override
    public int getMinTurnsWin(long playerId) {
        String getMinTurnsWinQuery = """
                SELECT COALESCE(MIN(number_of_turns), 0) AS min_number_of_turns
                FROM games
                WHERE player_id = ? AND result = 'WIN';
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMinTurnsWinQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt("min_number_of_turns");
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch min number of turns needed for a win for a player with ID: "
                    + playerId, exception);
        }
    }
}
