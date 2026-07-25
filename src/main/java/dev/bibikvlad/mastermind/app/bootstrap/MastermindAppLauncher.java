package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.app.bootstrap.errors.FatalPersistenceErrorHandler;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.ConsolePrinter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.input.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.core.MenuRunner;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.model.player.Player;

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
        try (ServiceContainer serviceContainer = new ServiceContainer()) {
            Parser parser = new ConsoleInputParser();

            runStartupFlow(serviceContainer, printer, parser);
        }
    }

    private static void runStartupFlow(ServiceContainer serviceContainer, Printer printer,
                                       Parser parser) {
        Optional<Player> optionalPlayer = serviceContainer.getPlayerService().loadLastSelectedPlayer();

        optionalPlayer.ifPresentOrElse(player -> startMainMenuFor(player, serviceContainer, printer, parser),
                () -> startFirstLaunch(serviceContainer, printer, parser));
    }

    private static void startMainMenuFor(Player player, ServiceContainer serviceContainer, Printer printer,
                                         Parser parser) {
        LocalizationContext localizationContext = new LocalizationContext(
                player.getPlayerConfig().locale());
        AppContext appContext = new AppContext(localizationContext, serviceContainer, printer, parser,
                player);

        Menu mainMenu = new MainMenu(appContext);

        MenuRunner.runMenu(mainMenu);
    }

    private static void startFirstLaunch(ServiceContainer serviceContainer, Printer printer,
                                         Parser parser) {
        FirstLaunchFlow firstLaunchFlow = new FirstLaunchFlow(serviceContainer, printer, parser);
        firstLaunchFlow.launch();
    }
}
