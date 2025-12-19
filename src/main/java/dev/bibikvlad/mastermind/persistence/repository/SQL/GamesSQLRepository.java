package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.model.game.Game;
import dev.bibikvlad.mastermind.persistence.dao.GamesDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.GamesRepository;

import java.util.List;

public class GamesSQLRepository implements GamesRepository {
    private final GamesDAO gamesDAO;
    private final TransactionManager transactionManager;

    public GamesSQLRepository(GamesDAO gamesDAO, TransactionManager transactionManager) {
        this.gamesDAO = gamesDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Game> findAll() throws PersistenceException {
        return List.of();
    }

    @Override
    public List<Game> findAllByPlayerId(long playerId) throws PersistenceException {
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
