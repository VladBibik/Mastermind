package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;

import java.util.List;
import java.util.Optional;

public class SQLPlayerConfigRepository {
    private final PlayerDAO playerDAO;
    private final TransactionManager transactionManager;

    public SQLPlayerConfigRepository(PlayerDAO playerDAO, TransactionManager transactionManager) {
        this.playerDAO = playerDAO;
        this.transactionManager = transactionManager;
    }

    public List<Player> findAll() throws PersistenceException {
        return playerDAO.findAll();
    }

    public Optional<Player> findById(long id) throws PersistenceException {
        return playerDAO.findById(id);
    }

    public Optional<Player> findByName(String name) throws PersistenceException {
        return playerDAO.findByName(name);
    }

    public void delete(Player player) throws PersistenceException {
        playerDAO.delete(player);
    }

    public void deleteById(long id) throws PersistenceException {
        playerDAO.deleteById(id);
    }

    public void deleteByName(String name) throws PersistenceException {
        playerDAO.deleteByName(name);
    }

    public boolean save(Player player) throws PersistenceException {
        boolean result = false;

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

    public boolean update(Player player) throws PersistenceException {
        boolean result = false;

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

    public boolean existById(long id) throws PersistenceException {
        return playerDAO.existsById(id);
    }

    public boolean existsByName(String name) throws PersistenceException {
        return playerDAO.existsByName(name);
    }
}
