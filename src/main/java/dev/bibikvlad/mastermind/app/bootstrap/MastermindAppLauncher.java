package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.input.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.FirstLaunch;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.MenuRunner;
import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.player.model.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class MastermindAppLauncher {
    public static void main(String[] args) throws SQLException {
        DatabaseContext.initialize();
        Connection connection = DatabaseContext.getConnection();

        MastermindUserInputParser parser = new ConsoleInputParser();

        ServiceContainer serviceContainer = new ServiceContainer(connection);
        try {
            launchGame(parser, serviceContainer);
        } catch (PersistenceException exception) {
            //TODO: Move to the handler class, and add localized message
            System.out.println("Problem with the database occurred. Please check your environment and try again later");

            System.exit(1);
        }
    }

    //TODO: This needs to be moved somewhere
    private static void launchGame(MastermindUserInputParser parser, ServiceContainer serviceContainer) {
        Optional<Player> optionalPlayer = serviceContainer.getPlayerService().loadLastSelectedPlayer();

        optionalPlayer.ifPresentOrElse(player -> {
                    LocalizationContext localizationContext = new LocalizationContext(
                            player.getPlayerConfig().locale());
                    Menu mainMenu = new MainMenu(localizationContext, serviceContainer, parser);

                    MenuRunner.runMenu(mainMenu);
                },
                () -> FirstLaunch.start(parser, serviceContainer));
    }
}
