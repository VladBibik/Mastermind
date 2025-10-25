package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

public class PlayerSQLRepository implements PlayerRepository {
    private final PlayerDAO playerDAO;
    private final TransactionManager transactionManager;

    public PlayerSQLRepository(PlayerDAO playerDAO, TransactionManager transactionManager) {
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

            throw new PersistenceException("Failed to save player " + player.getPlayerName(), exception);
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

            throw new PersistenceException("Failed to update player " + player.getPlayerName(), exception);
        }

        return result;
    }

    @Override
    public boolean updatePlayerName(long id, String name) throws PersistenceException {
        boolean result;
        try {
            transactionManager.begin();

            result = playerDAO.updatePlayerName(id, name);

            transactionManager.commit();
        } catch (PersistenceException exception) {
            try {
                transactionManager.rollback();
            } catch (PersistenceException rollbackException) {
                exception.addSuppressed(rollbackException);
            }

            throw new PersistenceException("Failed to update player's name " + name, exception);
        }

        return result;
    }

    @Override
    public void delete(Player player) throws PersistenceException {
        try {
            transactionManager.begin();

            playerDAO.delete(player);

            transactionManager.commit();
        } catch (PersistenceException exception) {
            try {
                transactionManager.rollback();
            } catch (PersistenceException rollbackException) {
                exception.addSuppressed(rollbackException);
            }

            throw new PersistenceException("Failed to delete player " + player.getPlayerName(), exception);
        }
    }

    @Override
    public void deleteById(long id) throws PersistenceException {
        try {
            transactionManager.begin();

            playerDAO.deleteById(id);

            transactionManager.commit();
        } catch (PersistenceException exception) {
            try {
                transactionManager.rollback();
            } catch (PersistenceException rollbackException) {
                exception.addSuppressed(rollbackException);
            }

            throw new PersistenceException("Failed to delete player with id: " + id, exception);
        }
    }

    @Override
    public void deleteByName(String name) throws PersistenceException {
        try {
            transactionManager.begin();

            playerDAO.deleteByName(name);

            transactionManager.commit();
        } catch (PersistenceException exception) {
            try {
                transactionManager.rollback();
            } catch (PersistenceException rollbackException) {
                exception.addSuppressed(rollbackException);
            }

            throw new PersistenceException("Failed to delete player with name: " + name, exception);
        }
    }

    @Override
    public boolean existsById(long id) throws PersistenceException {
        return playerDAO.existsById(id);
    }

    @Override
    public boolean existsByName(String name) throws PersistenceException {
        return playerDAO.existsByName(name);
    }

    @Override
    public int  count() throws PersistenceException {
        return playerDAO.count();
    }
}
