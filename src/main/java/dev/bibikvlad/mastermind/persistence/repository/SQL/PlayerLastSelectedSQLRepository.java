package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.PlayerLastSelectedDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.PlayerLastSelectedRepository;

public class PlayerLastSelectedSQLRepository implements PlayerLastSelectedRepository {
    private final PlayerLastSelectedDAO playerLastSelectedDAO;
    private final TransactionManager transactionManager;

    public PlayerLastSelectedSQLRepository(PlayerLastSelectedDAO playerLastSelectedDAO, TransactionManager transactionManager) {
        this.playerLastSelectedDAO = playerLastSelectedDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean saveOrUpdate(long id) throws PersistenceException {
        return false;
    }

    @Override
    public long getLastSelectedPlayerId() throws PersistenceException {
        return 0;
    }

    @Override
    public Player getLastSelectedPlayer() throws PersistenceException {
        return null;
    }
}
