package dev.bibikvlad.mastermind.persistence.database;

import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SchemaCreatorTest {
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        SchemaCreator.create(connection);
    }
}
