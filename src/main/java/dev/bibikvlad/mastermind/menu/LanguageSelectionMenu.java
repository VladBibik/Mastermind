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

            String userInput = parser.parseUserInput().toUpperCase();

            try {
                return selectLocaleFromIndex(userInput);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void printMenuOptions() {
        System.out.println("Please select a language");
        System.out.println("To select a language please print a corresponding number, " +
                "or write, for example: 'English'");
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

            if (selectedLocale == null) {
                throw new IllegalArgumentException("Provided " + userInput
                        + " is not a valid option for the selected languages");
            }
        }

        return selectedLocale;
    }
}
