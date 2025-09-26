package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.dao.PlayerConfigDAO;

import java.util.Optional;

public class SQLPlayerConfigRepository {
    private final PlayerConfigDAO playerConfigDAO;

    public SQLPlayerConfigRepository(PlayerConfigDAO playerConfigDAO) {
        this.playerConfigDAO = playerConfigDAO;
    }

    public Optional<PlayerConfig> findById(long playerId) throws PersistenceException {
        return playerConfigDAO.findById(playerId);
    }
}
