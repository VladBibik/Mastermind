package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;

public class FirstLaunchLanguageSelection {
    private static final String EMPTY_INPUT_ERROR = "Input cannot be empty. Please try again.";
    private static final String INVALID_INPUT_ERROR =
            "❌ Invalid input. Please enter a number corresponding to the menu option";
    private static final String MENU_OPTIONS = """
            Please select a language
            Enter a number corresponding to desired language
            
            1. English
            2. Russian
            """;

    private final Printer printer;
    private final Parser parser;

    public FirstLaunchLanguageSelection(Printer printer, Parser parser) {
        this.printer = printer;
        this.parser = parser;
    }

    public LocaleType selectLanguage() {
        while (true) {
            printMenuOptions();

            String userInput = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                printer.printMessage(EMPTY_INPUT_ERROR);

                continue;
            }

            LocaleType selectedLocale = parseLocaleSelection(userInput);

            if (selectedLocale != null) {
                return selectedLocale;
            }

            printer.printMessage(INVALID_INPUT_ERROR);
        }
    }

    private void printMenuOptions() {
        printer.printMessage(MENU_OPTIONS);
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
