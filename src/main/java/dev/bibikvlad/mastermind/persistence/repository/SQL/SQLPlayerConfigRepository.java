package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.dao.PlayerConfigDAO;

import java.util.Optional;

public class SQLPlayerConfigRepository implements PlayerConfigRepository {
    private final PlayerConfigDAO playerConfigDAO;

    public SQLPlayerConfigRepository(PlayerConfigDAO playerConfigDAO) {
        this.playerConfigDAO = playerConfigDAO;
    }

    @Override
    public Optional<PlayerConfig> findById(long playerId) throws PersistenceException {
        return playerConfigDAO.findById(playerId);
    }

    @Override
    public boolean update(long playerId, PlayerConfig playerConfig) throws PersistenceException {
        return playerConfigDAO.update(playerId, playerConfig);
    }

    @Override
    public boolean updateLocale(long playerId, LocaleType locale) throws PersistenceException {
        return playerConfigDAO.updateLocale(playerId, locale);
    }
}
