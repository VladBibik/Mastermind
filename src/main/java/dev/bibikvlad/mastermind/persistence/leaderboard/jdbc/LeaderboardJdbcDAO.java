package dev.bibikvlad.mastermind.persistence.leaderboard.jdbc;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.leaderboard.*;
import dev.bibikvlad.mastermind.persistence.leaderboard.dao.LeaderboardDAO;
import dev.bibikvlad.mastermind.persistence.leaderboard.mapper.*;

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
    public List<MainLeaderboardEntry> getOverallLeaderboard() {
        List<MainLeaderboardEntry> mainLeaderboardEntries = new ArrayList<>();
        String getMainLeaderboardQuery = """
                SELECT PLAYER.player_name, GAME.number_of_turns, GAME.duration_milliseconds
                FROM games GAME
                    JOIN players PLAYER
                        ON GAME.player_id = PLAYER.player_id
                WHERE result = 'WIN'
                ORDER BY GAME.number_of_turns ASC,
                         GAME.duration_milliseconds ASC,
                         PLAYER.player_name ASC
                LIMIT 10
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getMainLeaderboardQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mainLeaderboardEntries.add(MainLeaderboardEntryMapper.map(resultSet));
            }

            return mainLeaderboardEntries;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch leaderboard based on min turns needed to win", exception);
        }
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
    public List<WinPercentageLeaderboardEntry> getWinPercentageLeaderboard(int minGamesPlayed) {
        List<WinPercentageLeaderboardEntry> winsLeaderboardEntries = new ArrayList<>();
        String getWinRateLeaderboardQuery = """
                SELECT PLAYER.player_name,
                       COUNT(*) FILTER (WHERE GAME.result = 'WIN') * 100.0 / COUNT(*) AS win_percentage,
                       COUNT(*) AS games_played
                FROM games GAME
                    JOIN players PLAYER
                        ON GAME.player_id = PLAYER.player_id
                GROUP BY PLAYER.player_id,
                         PLAYER.player_name
                HAVING COUNT(*) >= ?
                ORDER BY win_percentage DESC,
                         games_played DESC,
                         PLAYER.player_name ASC
                LIMIT 10;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getWinRateLeaderboardQuery)) {
            preparedStatement.setInt(1, minGamesPlayed);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                winsLeaderboardEntries.add(WinPercentageMapper.map(resultSet));
            }

            return winsLeaderboardEntries;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch leaderboard based on win percentage", exception);
        }
    }

    @Override
    public List<WinsLeaderboardEntry> getWinsLeaderboard() {
        List<WinsLeaderboardEntry> winsLeaderboardEntries = new ArrayList<>();
        String getWinsLeaderboardQuery = """
                SELECT PLAYER.player_name, COUNT(*) as number_of_wins
                FROM games GAME
                         JOIN players PLAYER
                              ON GAME.player_id = PLAYER.player_id
                WHERE GAME.result = 'WIN'
                GROUP BY PLAYER.player_id, PLAYER.player_name
                LIMIT 10;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getWinsLeaderboardQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                winsLeaderboardEntries.add(WinLeaderboardEntryMapper.map(resultSet));
            }

            return winsLeaderboardEntries;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch leaderboard based on the number of wins", exception);
        }
    }
}
