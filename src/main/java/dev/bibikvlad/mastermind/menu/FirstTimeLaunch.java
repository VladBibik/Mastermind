package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.services.PlayerService;

public class FirstTimeLaunch {
    public static void launch(MastermindUserInputParser parser, PlayerService playerService) {
        System.out.println("Welcome to the Mastermind Game!");

        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        NewPlayerCreation.create(parser, playerService, localeType);
    }
}
