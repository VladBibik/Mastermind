package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;

public class FirstLaunchLanguageSelection {
    private static final String EMPTY_INPUT_ERROR =
            "❌ Input cannot be empty. Please enter a number corresponding to the menu option";
    private static final String INVALID_INPUT_ERROR =
            "❌ Invalid input. Please enter a number corresponding to the menu option";
    private static final String MENU_OPTIONS = """
            
            Please select a language.
            Enter the number corresponding to your choice.
            """;

    private final Printer printer;
    private final Parser parser;

    private boolean shouldRenderMenu = true;

    public FirstLaunchLanguageSelection(Printer printer, Parser parser) {
        this.printer = printer;
        this.parser = parser;
    }

    public LocalizationType selectLanguage() {
        while (true) {
            if (shouldRenderMenu) {
                printMenuOptions();

                shouldRenderMenu = false;
            }

            String userInput = parser.parse();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                printer.printMessage(EMPTY_INPUT_ERROR);

                continue;
            }

            LocalizationType selectedLocale = parseLocaleSelection(userInput);

            if (selectedLocale != null) {
                return selectedLocale;
            }

            printer.printMessage(INVALID_INPUT_ERROR);
        }
    }

    private void printMenuOptions() {
        printer.printMessage(MENU_OPTIONS);

        LocalizationType[] locales = LocalizationType.values();

        for (int i = 0; i < locales.length; i++) {
            String languageOption = (i + 1) + ". " + locales[i].getNativeDisplayName();

            printer.printMessage(languageOption);
        }
    }

    private LocalizationType parseLocaleSelection(String userInput) {
        int userInputIndex;

        try {
            userInputIndex = Integer.parseInt(userInput);

            return selectLocaleByIndex(userInputIndex);
        } catch (NumberFormatException _) {
            return null;
        }
    }

    private LocalizationType selectLocaleByIndex(int userInputIndex) {
        try {
            return LocalizationType.fromIndex(userInputIndex);
        } catch (IllegalArgumentException _) {
            return null;
        }
    }
}
