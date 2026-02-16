package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.menu.player.FirstTimePlayerCreation;
import dev.bibikvlad.mastermind.menu.settings.LanguageSelectionMenu;

public class FirstLaunchFlow {
    private final ServiceContainer serviceContainer;
    private final Printer printer;
    private final Parser parser;

    public FirstLaunchFlow(ServiceContainer serviceContainer, Printer printer, Parser parser) {
        this.serviceContainer = serviceContainer;
        this.printer = printer;
        this.parser = parser;
    }

    public void launch() {
        printer.printMessage("Welcome to the Mastermind Game!");

        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        FirstTimePlayerCreation firstTimePlayerCreation = new FirstTimePlayerCreation(localeType, serviceContainer,
                parser, printer);

        AppContext appContext = firstTimePlayerCreation.createPlayerAndGetContext();
        Menu menu;

        if (appContext == null) {
            menu = new ExitMenu(null);
        } else {
            menu = new MainMenu(appContext);
        }

        MenuRunner.runMenu(menu);
    }
}
