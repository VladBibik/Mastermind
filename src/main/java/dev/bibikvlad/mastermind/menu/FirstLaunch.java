package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.player.NewPlayerCreation;
import dev.bibikvlad.mastermind.menu.settings.LanguageSelectionMenu;

public class FirstLaunch {
    public static void start(AppContext appContext) {
        System.out.println("Welcome to the Mastermind Game!");

        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(appContext.parser());

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        LocalizationContext localizationContext = new LocalizationContext(localeType);
        appContext.serLocalizationContext(localizationContext);

        Menu mainMenu = new MainMenu(appContext);

        Menu playerCreationMenu = new NewPlayerCreation(appContext, localeType, mainMenu);

        MenuRunner.runMenu(playerCreationMenu);
    }
}
