package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.player.NewPlayerCreation;
import dev.bibikvlad.mastermind.menu.settings.LanguageSelectionMenu;
import dev.bibikvlad.mastermind.services.PlayerService;

public class FirstLaunch {
    public static void start(MastermindUserInputParser parser, ServiceContainer serviceContainer) {
        System.out.println("Welcome to the Mastermind Game!");

        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        LocalizationContext localizationContext = new LocalizationContext(localeType);

        Menu playerCreationMenu = new NewPlayerCreation(localizationContext, serviceContainer, parser, localeType,
                new ExitMenu(localizationContext, serviceContainer, parser));

        MenuRunner.runMenu(playerCreationMenu);
    }
}
