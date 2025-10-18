package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;

public class LanguageSelectionMenu {
    private final MastermindUserInputParser parser;

    public LanguageSelectionMenu(MastermindUserInputParser parser) {
        this.parser = parser;
    }

    public LocaleType selectLanguage() {
        while (true) {
            printMenuOptions();

            String userInput = parser.parseUserInput();

            LocaleType selectedLocale = selectLocaleFromIndex(userInput);

            if (selectedLocale != null) {
                return selectedLocale;
            }

            System.out.println("Provided '" + userInput + "' is not a valid option for the available languages.");
        }
    }

    private void printMenuOptions() {
        System.out.println("Please select a language");
        System.out.println("Type the number or name of the language, e.g. \"English\" or \"1\".");
        System.out.println("1. English");
        System.out.println("2. Russian");
    }

    private LocaleType selectLocaleFromIndex(String userInput) {
        try {
            int userInputIndex = Integer.parseInt(userInput);

            return LocaleType.fromLocaleIndex(userInputIndex);
        } catch (NumberFormatException exception) {
            return selectLocaleFromUserInput(userInput);
        }
    }

    private LocaleType selectLocaleFromUserInput(String userInput) {
        LocaleType selectedLocale = LocaleType.fromLocaleString(userInput);

        if (selectedLocale == null) {
            selectedLocale = LocaleType.fromLanguageString(userInput);
        }

        return selectedLocale;
    }
}
