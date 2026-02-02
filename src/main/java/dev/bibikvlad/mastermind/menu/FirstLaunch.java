package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.menu.player.FirstTimePlayerCreation;
import dev.bibikvlad.mastermind.menu.settings.LanguageSelectionMenu;

public class FirstLaunch {
    public static void start(ServiceContainer serviceContainer, Printer printer, MastermindUserInputParser parser) {
        System.out.println("Welcome to the Mastermind Game!");

        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        FirstTimePlayerCreation firstTimePlayerCreation = new FirstTimePlayerCreation(localeType, serviceContainer,
                parser, printer);

        AppContext appContext = firstTimePlayerCreation.createPlayerAndGetContext();

        Menu mainMenu = new MainMenu(appContext);
        MenuRunner.runMenu(mainMenu);
    }
}
