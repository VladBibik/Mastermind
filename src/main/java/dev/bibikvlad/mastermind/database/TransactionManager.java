package dev.bibikvlad.mastermind.database;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private final Connection connection;

    public TransactionManager(Connection connection) {
        this.connection = connection;
    }

    public void begin() throws PersistenceException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to begin transaction", exception);
        }
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void rollback() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            //TODO: Add handling
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception1) {
                //TODO: Add handling
            }
        }
    }
}
