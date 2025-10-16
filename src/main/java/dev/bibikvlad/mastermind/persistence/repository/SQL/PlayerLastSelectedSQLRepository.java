package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.repository.PlayerLastSelectedRepository;

public class PlayerLastSelectedSQLRepository implements PlayerLastSelectedRepository {
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
