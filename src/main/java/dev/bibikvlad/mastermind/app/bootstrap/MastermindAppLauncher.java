package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.input.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.FirstLaunch;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.MenuRunner;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.persistence.player.jdbc.PlayerConfigJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.jdbc.PlayerJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.jdbc.PlayerLastSelectedJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerConfigDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerLastSelectedDAO;
import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerLastSelectedRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerConfigSQLRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerLastSelectedSQLRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerSQLRepository;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class MastermindAppLauncher {
    public static void main(String[] args) throws SQLException {
        DatabaseContext.initialize();
        Connection connection = DatabaseContext.getConnection();

        PlayerDAO playerDAO = new PlayerJdbcDAO(connection);
        PlayerConfigDAO playerConfigDAO = new PlayerConfigJdbcDAO(connection);
        PlayerLastSelectedDAO playerLastSelectedDAO = new PlayerLastSelectedJdbcDAO(connection);

        TransactionManager transactionManager = new TransactionManager(connection);

        PlayerRepository playerRepository = new PlayerSQLRepository(playerDAO, transactionManager);
        PlayerConfigRepository playerConfigRepository =
                new PlayerConfigSQLRepository(playerConfigDAO, transactionManager);
        PlayerLastSelectedRepository playerLastSelectedRepository =
                new PlayerLastSelectedSQLRepository(playerLastSelectedDAO, transactionManager);

        PlayerService playerService = new PlayerService(playerRepository, playerConfigRepository,
                playerLastSelectedRepository);

        MastermindUserInputParser parser = new ConsoleInputParser();

        try {
            launchGame(parser, playerService);
        } catch (PersistenceException exception) {
            //TODO: Move to the handler class, and add localized message
            System.out.println("Problem with the database occurred. Please check your environment and try again later");

            System.exit(1);
        }
    }

    private static void launchGame(MastermindUserInputParser parser, PlayerService playerService) {
        Optional<Player> optionalPlayer = playerService.loadLastSelectedPlayer();

        optionalPlayer.ifPresentOrElse(player -> {
                    LocalizationContext localizationContext = new LocalizationContext(
                            player.getPlayerConfig().locale());
                    Menu mainMenu = new MainMenu(localizationContext, parser, playerService);

                    MenuRunner.runMenu(mainMenu);
                },
                () -> FirstLaunch.start(parser, playerService));
    }
}
