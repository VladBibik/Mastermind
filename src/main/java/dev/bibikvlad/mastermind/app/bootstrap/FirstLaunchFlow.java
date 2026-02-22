package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.menu.ExitMenu;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.MenuRunner;
import dev.bibikvlad.mastermind.menu.player.FirstTimePlayerCreation;
import dev.bibikvlad.mastermind.menu.settings.FirstLaunchLanguageSelection;

public class FirstLaunchFlow {
    private static final String WELCOME_MESSAGE = "Welcome to the Mastermind Game!";

    private final ServiceContainer serviceContainer;
    private final Printer printer;
    private final Parser parser;

    public FirstLaunchFlow(ServiceContainer serviceContainer, Printer printer, Parser parser) {
        this.serviceContainer = serviceContainer;
        this.printer = printer;
        this.parser = parser;
    }

    public void launch() {
        printer.printMessage(WELCOME_MESSAGE);

        AppContext appContext = createPlayerAndContext(selectLanguage());

        startMenu(appContext);
    }

    private LocaleType selectLanguage() {
        FirstLaunchLanguageSelection firstLaunchLanguageSelection = new FirstLaunchLanguageSelection(printer, parser);

        return firstLaunchLanguageSelection.selectLanguage();
    }

    private AppContext createPlayerAndContext(LocaleType localeType) {
        FirstTimePlayerCreation firstTimePlayerCreation =
                new FirstTimePlayerCreation(localeType, serviceContainer, parser, printer);

        return firstTimePlayerCreation.createPlayerAndGetContext();
    }

    private void startMenu(AppContext appContext) {
        Menu menu;

        if (appContext == null) {
            menu = new ExitMenu(null);
        } else {
            menu = new MainMenu(appContext);
        }

        MenuRunner.runMenu(menu);
    }
}
