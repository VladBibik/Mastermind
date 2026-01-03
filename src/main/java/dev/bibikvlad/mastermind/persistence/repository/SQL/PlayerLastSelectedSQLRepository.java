package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerLastSelectedDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.PlayerLastSelectedRepository;

import java.util.Optional;

public class PlayerLastSelectedSQLRepository implements PlayerLastSelectedRepository {
    private final PlayerLastSelectedDAO playerLastSelectedDAO;
    private final TransactionManager transactionManager;

    public PlayerLastSelectedSQLRepository(PlayerLastSelectedDAO playerLastSelectedDAO,
                                           TransactionManager transactionManager) {
        this.playerLastSelectedDAO = playerLastSelectedDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public long getLastSelectedPlayerId() {
        return playerLastSelectedDAO.getLastSelectedPlayerId();
    }

    @Override
    public Optional<Player> getLastSelectedPlayer() {
        return playerLastSelectedDAO.getLastSelectedPlayer();
    }

    @Override
    public boolean saveOrUpdate(long id) {
        boolean result;

        try {
            transactionManager.begin();

            result = playerLastSelectedDAO.saveOrUpdate(id);

            transactionManager.commit();
        } catch (PersistenceException exception) {
            try {
                transactionManager.rollback();
            } catch (PersistenceException rollbackException) {
                rollbackException.addSuppressed(exception);
            }

            throw exception;
        }

        return result;
    }
}
