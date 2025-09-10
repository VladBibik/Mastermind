package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.sql.Connection;
import java.util.Optional;

public class JdbcPlayerConfigDAO implements PlayerConfigDAO {
    private final Connection connection;

    public JdbcPlayerConfigDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<PlayerConfig> findById(int id) throws PersistenceException {
        return Optional.empty();
    }

    @Override
    public boolean save(PlayerConfig playerConfig) throws PersistenceException {
        return false;
    }

    @Override
    public boolean delete(PlayerConfig playerConfig) throws PersistenceException {
        return false;
    }

    @Override
    public boolean update(Player player) throws PersistenceException {
        return false;
    }

    @Override
    public boolean updateLocale(int playerId, LocaleType locale) throws PersistenceException {
        return false;
    }
}
