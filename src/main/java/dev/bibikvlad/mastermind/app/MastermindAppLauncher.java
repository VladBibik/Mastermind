package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.FirstTimeLaunch;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.JDBC.PlayerConfigJdbcDAO;
import dev.bibikvlad.mastermind.persistence.dao.JDBC.PlayerJdbcDAO;
import dev.bibikvlad.mastermind.persistence.dao.JDBC.PlayerLastSelectedJdbcDAO;
import dev.bibikvlad.mastermind.persistence.dao.PlayerConfigDAO;
import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.dao.PlayerLastSelectedDAO;
import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerLastSelectedRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;
import dev.bibikvlad.mastermind.persistence.repository.SQL.PlayerConfigSQLRepository;
import dev.bibikvlad.mastermind.persistence.repository.SQL.PlayerLastSelectedSQLRepository;
import dev.bibikvlad.mastermind.persistence.repository.SQL.PlayerSQLRepository;
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

        LocalizationContext defaultLocalizationContext = getLocalizationContext(playerService, parser);


        MainMenu gameMenu = new MainMenu(defaultLocalizationContext, parser, playerService);

        gameMenu.menu();
    }

    private static LocalizationContext getLocalizationContext(PlayerService playerService,
                                                              MastermindUserInputParser parser) {
        Optional<Player> player = playerService.loadLastSelectedPlayer();

        if (player.isPresent()) {
            return new LocalizationContext(player.get().getPlayerConfig().getLocale());
        }

        FirstTimeLaunch.launch(parser, playerService);

        return getLocalizationContext(playerService, parser);
    }
}
