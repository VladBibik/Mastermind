package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;

public class FirstLaunchLanguageSelection {
    private final Printer printer;
    private final Parser parser;
    private final FirstLaunchLanguageSelectionMessages messages;

    private boolean shouldRenderMenu = true;

    public FirstLaunchLanguageSelection(Printer printer, Parser parser) {
        this.printer = printer;
        this.parser = parser;
        this.messages = new FirstLaunchLanguageSelectionMessages();
    }

    public LocalizationType selectLanguage() {
        while (true) {
            if (shouldRenderMenu) {
                printMenuOptions();

                printer.printMessage(messages.getTerminalTestString());

                shouldRenderMenu = false;
            }

            String userInput = parser.parse();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                printer.printMessage(messages.getEmptyInputMessage());

                continue;
            }

            LocalizationType selectedLocale = parseLocalizationTypeSelection(userInput);

            if (selectedLocale != null) {
                return selectedLocale;
            }

            printer.printMessage(messages.getInvalidInputMessage());
        }
    }

    private void printMenuOptions() {
        printer.printMessage(messages.getMenuOptions());

        LocalizationType[] locales = LocalizationType.values();

        for (int i = 0; i < locales.length; i++) {
            String languageOption = (i + 1) + ". " + locales[i].getNativeDisplayName();

            printer.printMessage(languageOption);
        }
    }

    private LocalizationType parseLocalizationTypeSelection(String userInput) {
        int userInputIndex;

        try {
            userInputIndex = Integer.parseInt(userInput);

            return selectLocalizationType(userInputIndex);
        } catch (NumberFormatException _) {
            return null;
        }
    }

    private LocalizationType selectLocalizationType(int userInputIndex) {
        try {
            return LocalizationType.fromIndex(userInputIndex);
        } catch (IllegalArgumentException _) {
            return null;
        }
    }
}
