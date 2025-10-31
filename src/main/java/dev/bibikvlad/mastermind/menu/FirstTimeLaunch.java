package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.services.PlayerService;

//TODO: Def needs to be refactored. Naming and logic should be questioned
public class FirstTimeLaunch {
    public static LocalizationContext getLocalizationContextAfterUserSelection(MastermindUserInputParser parser,
                                                                               PlayerService playerService) {
        System.out.println("Welcome to the Mastermind Game!");

        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        LocalizationContext localizationContext = new LocalizationContext(localeType);

        boolean isClosed = NewPlayerCreation.create(parser, playerService, localeType);

        if (isClosed) {
            return null;
        }

        return localizationContext;
    }
}
