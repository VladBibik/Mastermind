package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.app.bootstrap.errors.FatalPersistenceErrorHandler;
import dev.bibikvlad.mastermind.app.printer.ConsolePrinter;
import dev.bibikvlad.mastermind.app.printer.Printer;
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
import java.util.Optional;

public class MastermindAppLauncher {
    public static void main(String[] args) {
        Printer printer = new ConsolePrinter();

        try {
            run(printer);
        } catch (PersistenceException exception) {
            FatalPersistenceErrorHandler handler = new FatalPersistenceErrorHandler(printer);

            handler.handle(exception);
        }
    }

    private static void run(Printer printer) throws PersistenceException {
        DatabaseContext.initialize();
        Connection connection = DatabaseContext.getConnection();

        try (ServiceContainer serviceContainer = new ServiceContainer(connection)) {
            MastermindUserInputParser parser = new ConsoleInputParser();

            selectAndRunStartupRoutine(serviceContainer, printer, parser);
        }
    }

    private static void selectAndRunStartupRoutine(ServiceContainer serviceContainer, Printer printer,
                                                   MastermindUserInputParser parser) {
        Optional<Player> optionalPlayer = serviceContainer.getPlayerService().loadLastSelectedPlayer();

        optionalPlayer.ifPresentOrElse(player -> {
                    LocalizationContext localizationContext = new LocalizationContext(
                            player.getPlayerConfig().locale());
                    AppContext appContext = new AppContext(localizationContext, serviceContainer, printer, parser,
                            player);

                    Menu mainMenu = new MainMenu(appContext);

                    MenuRunner.runMenu(mainMenu);
                },
                () -> FirstLaunch.start(serviceContainer, printer, parser));
    }
}
