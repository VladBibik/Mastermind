package dev.bibikvlad.mastermind.database;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private final Connection connection;

    public TransactionManager(Connection connection) {
        this.connection = connection;
    }

    public void begin() throws SQLException {
        connection.setAutoCommit(false);
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
