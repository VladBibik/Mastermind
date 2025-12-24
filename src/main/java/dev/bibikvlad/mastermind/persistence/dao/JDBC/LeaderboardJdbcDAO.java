package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.leaderboard.TimeLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.TurnsLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinPercentageLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinsLeaderboardEntry;
import dev.bibikvlad.mastermind.persistence.dao.LeaderboardDAO;
import dev.bibikvlad.mastermind.persistence.mappers.leaderboards.TimeLeaderboardEntryMapper;
import dev.bibikvlad.mastermind.persistence.mappers.leaderboards.TurnsLeaderboardEntryMapper;
import dev.bibikvlad.mastermind.persistence.mappers.leaderboards.WinPercentageMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardJdbcDAO implements LeaderboardDAO {
    private final Connection connection;

    public LeaderboardJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<TimeLeaderboardEntry> getTimeLeaderboard() {
        List<TimeLeaderboardEntry> timeLeaderboardEntries = new ArrayList<>();
        String timeLeaderboardQuery = """
                SELECT PLAYER.player_name, GAME.duration_milliseconds
                FROM games GAME
                JOIN players PLAYER
                    ON GAME.player_id = PLAYER.player_id
                WHERE result = 'WIN'
                ORDER BY GAME.duration_milliseconds ASC,
                         GAME.number_of_turns ASC,
                         PLAYER.player_name ASC
                LIMIT 10
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(timeLeaderboardQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                timeLeaderboardEntries.add(TimeLeaderboardEntryMapper.map(resultSet));
            }

            return timeLeaderboardEntries;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch leaderboard based on time needed for a win", exception);
        }
    }

    @Override
    public List<TurnsLeaderboardEntry> getTurnsLeaderboard() {
        List<TurnsLeaderboardEntry> turnsLeaderboardEntries = new ArrayList<>();
        String turnsLeaderboardQuery = """
                SELECT PLAYER.player_name, GAME.number_of_turns
                FROM games GAME
                JOIN players PLAYER
                    ON GAME.player_id = PLAYER.player_id
                WHERE result = 'WIN'
                ORDER BY GAME.number_of_turns ASC,
                         GAME.duration_milliseconds ASC,
                         PLAYER.player_name ASC
                LIMIT 10
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(turnsLeaderboardQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                turnsLeaderboardEntries.add(TurnsLeaderboardEntryMapper.map(resultSet));
            }

            return turnsLeaderboardEntries;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch leaderboard based on turns needed for a win", exception);
        }
    }

    @Override
    public List<WinPercentageLeaderboardEntry> getWinRateLeaderboard() {
        List<WinPercentageLeaderboardEntry> winsLeaderboardEntries = new ArrayList<>();
        String getWinRateLeaderboardQuery = """
                SELECT PLAYER.player_name,
                       COUNT(*) FILTER (WHERE GAME.result = 'WIN') * 100.0 / COUNT(*) AS win_percentage
                FROM games GAME
                JOIN players PLAYER
                    ON GAME.player_id = PLAYER.player_id
                GROUP BY PLAYER.player_id,
                         PLAYER.player_name
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getWinRateLeaderboardQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                winsLeaderboardEntries.add(WinPercentageMapper.map(resultSet));
            }

            return winsLeaderboardEntries;

        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch win percentage based leaderboard data", exception);
        }
    }

    @Override
    public List<WinsLeaderboardEntry> getWinsLeaderboard() {
        return List.of();
    }
}
