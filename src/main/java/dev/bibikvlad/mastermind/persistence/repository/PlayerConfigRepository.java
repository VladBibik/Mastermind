package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;

public class PlayerConfigRepository {
    private final PlayerDAO playerDAO;
    private final TransactionManager transactionManager;

    public PlayerConfigRepository(PlayerDAO playerDAO, TransactionManager transactionManager) {
        this.playerDAO = playerDAO;
        this.transactionManager = transactionManager;
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
                //TODO: Add exception handling!
                exception.addSuppressed(rollbackException);
            }

            throw exception;
        }

        return result;
    }
}
