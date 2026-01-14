package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
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

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                System.out.println("Input cannot be empty. Please try again.");

                continue;
            }

            LocaleType selectedLocale = parseLocaleSelection(userInput);

            if (selectedLocale != null) {
                return selectedLocale;
            }

            System.out.println("Provided '" + userInput + "' is not a valid index for the available languages.");
        }
    }

    private void printMenuOptions() {
        System.out.println("Please select a language");
        System.out.println("Enter a number corresponding to desired language.");
        System.out.println();
        System.out.println("1. English");
        System.out.println("2. Russian");
    }

    private LocaleType parseLocaleSelection(String userInput) {
        int userInputIndex;

        try {
            userInputIndex = Integer.parseInt(userInput);

            return selectLocaleByIndex(userInputIndex);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    private LocaleType selectLocaleByIndex(int userInputIndex) {
        try {
            return LocaleType.fromLocaleIndex(userInputIndex);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
