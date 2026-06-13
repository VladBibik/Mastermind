package dev.bibikvlad.mastermind.persistence.player.repository.sql;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerRepository;

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
    public List<Player> findAll() {
        return playerDAO.findAll();
    }

    @Override
    public Optional<Player> findById(long id) {
        return playerDAO.findById(id);
    }

    @Override
    public Optional<Player> findByName(String name) {
        return playerDAO.findByName(name);
    }

    @Override
    public Player save(Player player) {
        Player createdPlayer;

        try {
            transactionManager.begin();

            createdPlayer = playerDAO.save(player);

            transactionManager.commit();
        } catch (PersistenceException exception) {
            try {
                transactionManager.rollback();
            } catch (PersistenceException rollbackException) {
                exception.addSuppressed(rollbackException);
            }

            throw exception;
        }

        return createdPlayer;
    }

    @Override
    public boolean update(Player player) {
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
    public boolean updatePlayerName(long id, String name) {
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

            throw exception;
        }

        return result;
    }

    @Override
    public void delete(Player player) {
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

            throw exception;
        }
    }

    @Override
    public void deleteById(long id) {
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

            throw exception;
        }
    }

    @Override
    public void deleteByName(String name) {
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

            throw exception;
        }
    }

    @Override
    public boolean existsById(long id) {
        return playerDAO.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return playerDAO.existsByName(name);
    }

    @Override
    public int  count() {
        return playerDAO.count();
    }
}
