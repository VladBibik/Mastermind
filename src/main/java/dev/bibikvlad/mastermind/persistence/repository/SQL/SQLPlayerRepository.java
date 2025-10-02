package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

public class SQLPlayerRepository implements PlayerRepository {
    private final PlayerDAO playerDAO;
    private final TransactionManager transactionManager;

    public SQLPlayerRepository(PlayerDAO playerDAO, TransactionManager transactionManager) {
        this.playerDAO = playerDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Player> findAll() throws PersistenceException {
        return playerDAO.findAll();
    }

    @Override
    public Optional<Player> findById(long id) throws PersistenceException {
        return playerDAO.findById(id);
    }

    @Override
    public Optional<Player> findByName(String name) throws PersistenceException {
        return playerDAO.findByName(name);
    }

    @Override
    public void delete(Player player) throws PersistenceException {
        playerDAO.delete(player);
    }

    @Override
    public void deleteById(long id) throws PersistenceException {
        playerDAO.deleteById(id);
    }

    @Override
    public void deleteByName(String name) throws PersistenceException {
        playerDAO.deleteByName(name);
    }

    @Override
    public boolean save(Player player) throws PersistenceException {
        boolean result;

        try {
            transactionManager.begin();

            result = playerDAO.save(player);

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
    public boolean update(Player player) throws PersistenceException {
        boolean result;

        try {
            transactionManager.begin();

            result = playerDAO.update(player);

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
    public boolean existsById(long id) throws PersistenceException {
        return playerDAO.existsById(id);
    }

    @Override
    public boolean existsByName(String name) throws PersistenceException {
        return playerDAO.existsByName(name);
    }
}
