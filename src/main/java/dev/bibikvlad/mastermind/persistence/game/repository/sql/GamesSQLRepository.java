package dev.bibikvlad.mastermind.persistence.game.repository.sql;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.persistence.game.model.Game;
import dev.bibikvlad.mastermind.persistence.game.dao.GameDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.game.repository.GamesRepository;

import java.util.List;

public class GamesSQLRepository implements GamesRepository {
    private final GameDAO gamesDAO;
    private final TransactionManager transactionManager;

    public GamesSQLRepository(GameDAO gamesDAO, TransactionManager transactionManager) {
        this.gamesDAO = gamesDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Game> findAll() throws PersistenceException {
        return gamesDAO.findAll();
    }

    @Override
    public List<Game> findAllByPlayerId(long playerId) {
        return gamesDAO.findAllByPlayerId(playerId);
    }

    @Override
    public boolean save(long playerId, GameData gameData) {
        boolean result;

        try {
            transactionManager.begin();

            result = gamesDAO.save(playerId, gameData);

            transactionManager.commit();
        } catch (PersistenceException exception) {
            try {
                transactionManager.rollback();
            } catch (PersistenceException rollbackException) {
                exception.addSuppressed(rollbackException);
            }

            throw exception;
        }

        return result;
    }

    @Override
    public int count() throws PersistenceException {
        return gamesDAO.count();
    }

    @Override
    public int countByPlayerId(long playerId) {
        return gamesDAO.countByPlayerId(playerId);
    }
}
