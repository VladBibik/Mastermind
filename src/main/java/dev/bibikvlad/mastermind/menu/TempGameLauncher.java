package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.game.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.persistence.dao.JDBC.PlayerConfigJdbcDAO;
import dev.bibikvlad.mastermind.persistence.dao.JDBC.PlayerJdbcDAO;
import dev.bibikvlad.mastermind.persistence.dao.PlayerConfigDAO;
import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;
import dev.bibikvlad.mastermind.persistence.repository.SQL.PlayerConfigSQLRepository;
import dev.bibikvlad.mastermind.persistence.repository.SQL.PlayerSQLRepository;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.sql.Connection;
import java.sql.SQLException;

public class TempGameLauncher {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseContext.getConnection();

        PlayerDAO playerDAO = new PlayerJdbcDAO(connection);
        PlayerConfigDAO playerConfigDAO = new PlayerConfigJdbcDAO(connection);

        TransactionManager transactionManager = new TransactionManager(connection);

        PlayerRepository playerRepository = new PlayerSQLRepository(playerDAO, transactionManager);
        PlayerConfigRepository playerConfigRepository =
                new PlayerConfigSQLRepository(playerConfigDAO, transactionManager);

        PlayerService playerService = new PlayerService(playerRepository, playerConfigRepository);

        GameMenu gameMenu = new GameMenu(new ConsoleInputParser(), playerService);

        gameMenu.displayMenu();
    }
}
