package dev.bibikvlad.mastermind.persistence.game.jdbc;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.model.game.Game;
import dev.bibikvlad.mastermind.persistence.game.dao.GameDAO;
import dev.bibikvlad.mastermind.persistence.game.mapper.GameMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameJdbcDAO implements GameDAO {
    private final Connection connection;

    public GameJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Game> findAll() {
        List<Game> games = new ArrayList<>();
        String findAllQuery = """
                SELECT game_id, player_id, duration_milliseconds, result, number_of_turns, started_at
                FROM games
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                games.add(GameMapper.map(resultSet));
            }

            return games;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch all games", exception);
        }
    }

    @Override
    public List<Game> findAllByPlayerId(long playerId) {
        List<Game> games = new ArrayList<>();
        String findAllByPlayerIdQuery = """
                SELECT game_id, player_id, duration_milliseconds, result, number_of_turns, started_at
                FROM games
                WHERE player_id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllByPlayerIdQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                games.add(GameMapper.map(resultSet));
            }

            return games;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch all games by Player ID: " + playerId, exception);
        }
    }

    @Override
    public boolean save(long playerId, GameData gameData) {
        int rowsAffected;
        String saveQuery = """
                INSERT INTO games (player_id, duration_milliseconds, result, number_of_turns)
                VALUES(?, ?, ?, ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            preparedStatement.setLong(1, playerId);
            preparedStatement.setLong(2, gameData.getGameDuration());
            preparedStatement.setString(3, gameData.getGameOutcome().getResult().name());
            preparedStatement.setInt(4, gameData.getGameOutcome().getTurnsPlayed());

            rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to save a a game’s data for a player with ID: " + playerId,
                    exception);
        }
    }

    @Override
    public int count() {
        String countQuery = """
                SELECT COUNT(game_id)
                FROM games
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(countQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to count the number of rows in the games table", exception);
        }
    }

    @Override
    public int countByPlayerId(long playerId) {
        String countByPlayerIdQuery = """
                SELECT COUNT(game_id)
                FROM games
                WHERE player_id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(countByPlayerIdQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to count the number of rows in the " +
                    "games table for a player with ID: " + playerId, exception);
        }
    }
}
