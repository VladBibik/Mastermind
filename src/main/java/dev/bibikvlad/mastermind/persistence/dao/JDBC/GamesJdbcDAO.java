package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.model.game.Game;
import dev.bibikvlad.mastermind.persistence.dao.GamesDAO;

import java.util.List;

public class GamesJdbcDAO implements GamesDAO {
    @Override
    public List<Game> findAll() throws PersistenceException {
        return List.of();
    }

    @Override
    public List<Game> findAllById() throws PersistenceException {
        return List.of();
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
