package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.model.game.Game;
import dev.bibikvlad.mastermind.persistence.dao.GamesDAO;
import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.mappers.GameMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GamesJdbcDAO implements GamesDAO {
    @Override
    public List<Game> findAll() throws PersistenceException {
        List<Game> games = new ArrayList<>();
        String findAllQuery = """
                SELECT *
                FROM games
                """;

        try (PreparedStatement preparedStatement = DatabaseContext.getConnection().prepareStatement(findAllQuery)) {
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
    public List<Game> findAllByPlayerId(long playerId) throws PersistenceException {
        List<Game> games = new ArrayList<>();
        String findAllByPlayerIdQuery = """
                SELECT *
                FROM games
                WHERE player_id = ?
                """;

        try (PreparedStatement preparedStatement = DatabaseContext.getConnection()
                .prepareStatement(findAllByPlayerIdQuery)) {
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
    public boolean save(long playerId, GameData gameData) throws PersistenceException {
        return false;
    }

    @Override
    public int count() throws PersistenceException {
        return 0;
    }
}
