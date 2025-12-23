package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.leaderboard.TimeLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.TurnsLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinPercentageLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinRateLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinsLeaderboardEntry;
import dev.bibikvlad.mastermind.persistence.dao.LeaderboardDAO;
import dev.bibikvlad.mastermind.persistence.mappers.TimeLeaderboardEntryMapper;

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
                ORDER BY GAME.number_of_turns ASC,
                         GAME.duration_milliseconds ASC,
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
        return List.of();
    }

    @Override
    public List<WinPercentageLeaderboardEntry> getWinRateLeaderboard() {
        return List.of();
    }

    @Override
    public List<WinsLeaderboardEntry> getWinsLeaderboard() {
        return List.of();
    }
}
