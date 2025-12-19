package dev.bibikvlad.mastermind.persistence.database;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private final Connection connection;

    public TransactionManager(Connection connection) {
        this.connection = connection;
    }

    public void begin() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to begin transaction", exception);
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to commit transaction", exception);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                throw new PersistenceException("Failed to restore auto-commit after transaction", exception);
            }
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to rollback transaction", exception);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                throw new PersistenceException("Failed to restore auto-commit after transaction", exception);
            }
        }
    }
}
