package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;

public class PlayerConfigRepository {
    private final PlayerDAO playerDAO;
    private final TransactionManager transactionManager;

    public PlayerConfigRepository(PlayerDAO playerDAO, TransactionManager transactionManager) {
        this.playerDAO = playerDAO;
        this.transactionManager = transactionManager;
    }
}
