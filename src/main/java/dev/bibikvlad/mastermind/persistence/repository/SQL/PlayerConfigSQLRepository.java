package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.dao.PlayerConfigDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;

import java.util.Optional;

public class PlayerConfigSQLRepository implements PlayerConfigRepository {
    private final PlayerConfigDAO playerConfigDAO;
    private final TransactionManager transactionManager;

    public PlayerConfigSQLRepository(PlayerConfigDAO playerConfigDAO, TransactionManager transactionManager) {
        this.playerConfigDAO = playerConfigDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public Optional<PlayerConfig> findById(long playerId) throws PersistenceException {
        return playerConfigDAO.findById(playerId);
    }

    @Override
    public boolean update(long playerId, PlayerConfig playerConfig) throws PersistenceException {
        boolean result;

        try {
            transactionManager.begin();

            result = playerConfigDAO.update(playerId, playerConfig);

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

    @Override
    public boolean updateLocale(long playerId, LocaleType locale) throws PersistenceException {
        boolean result;

        try {
            transactionManager.begin();

            result = playerConfigDAO.updateLocale(playerId, locale);

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

    @Override
    public boolean updateLogoColors(long playerId, LogoColorsBundle logoColorsBundle) throws PersistenceException {
        boolean result;

        try {
            transactionManager.begin();

            result = playerConfigDAO.updateLogoColors(playerId, logoColorsBundle);

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
