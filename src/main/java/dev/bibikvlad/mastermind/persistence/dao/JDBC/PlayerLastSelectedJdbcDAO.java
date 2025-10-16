package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.persistence.dao.PlayerLastSelectedDAO;

import java.sql.Connection;

public class PlayerLastSelectedJdbcDAO implements PlayerLastSelectedDAO {
    private final Connection connection;

    public PlayerLastSelectedJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean saveOrUpdate(int id) throws PersistenceException {
        return false;
    }

    @Override
    public int getLastSelected() throws PersistenceException {
        return 0;
    }
}
