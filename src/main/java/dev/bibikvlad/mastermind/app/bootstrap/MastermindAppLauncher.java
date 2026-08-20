package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.app.bootstrap.TEMP_TEST.RussianCharactersTest;
import dev.bibikvlad.mastermind.app.bootstrap.TEMP_TEST.SoutTest;
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
import dev.bibikvlad.platform.windows.WindowsConsoleConfigurator;

import java.util.Optional;

public class MastermindAppLauncher {
    public static void main(String[] args) {
        WindowsConsoleConfigurator.enableUtf8();

        SoutTest soutTest = new SoutTest();
        soutTest.test();
        soutTest.testClueSymbols();

        Printer printer = new ConsolePrinter();

        System.out.println("=== System.out ===");
        System.out.println("Русский ○ ◍ ● ⬤ ◉ 😀 🎉");
        System.out.println("\u001B[91mSYSTEM.OUT RED\u001B[0m");

        System.out.println();

        System.out.println("=== ConsolePrinter ===");
        printer.printMessage("Русский ○ ◍ ● ⬤ ◉ 😀 🎉");
        printer.printMessage("\u001B[91mCONSOLE PRINTER RED\u001B[0m");

        System.out.println();

        RussianCharactersTest ruTest = new RussianCharactersTest();
        ruTest.test();
        soutTest.testClueSymbols(printer);

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
