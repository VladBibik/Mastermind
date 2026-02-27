package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.context.AppContextFactory;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class LanguageSelectionMenu extends Menu {
    private final PlayerService playerService;
    private final Printer printer;
    private final Parser parser;

    public LanguageSelectionMenu(AppContext appContext) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
        this.printer = appContext.printer();
        this.parser = appContext.parser();
    }

    @Override
    public Menu run() {
        printMenuOptions();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::selectLocaleByIndex)
                .orElse(new SettingsMenu(appContext));
    }

    private void printMenuOptions() {
        printer.printMessage("""
                Please select a language.
                Enter the number corresponding to your choice.
                Enter '0' to return to the Settings menu.
                """);

        for (LocaleType locale : LocaleType.values()) {
            printer.printMessage(locale.getNativeDisplayName());
        }
    }

    private Menu selectLocaleByIndex(int userInputIndex) {
        try {
            return checkLanguageSelection(LocaleType.fromIndex(userInputIndex));
        } catch (IllegalArgumentException exception) {
            printer.printMessage("❌ Invalid input. Please enter a number corresponding to the menu option");

            return this;
        }
    }

    private Menu checkLanguageSelection(LocaleType localeType) {
        if (localeType.equals(appContext.currentPlayer().getPlayerConfig().locale())) {
            printer.printMessage("Language is already selected.");

            return this;
        } else {
            printer.printMessage("Language changed to: " + localeType.getNativeDisplayName());

            return updateLanguage(localeType);
        }
    }

    private Menu updateLanguage(LocaleType localeType) {
        playerService.updatePlayerLocale(appContext.currentPlayer().getId(), localeType);

        return createNewContext(localeType);
    }

    private Menu createNewContext(LocaleType localeType) {
        return backToSettings(AppContextFactory.withLocale(appContext, localeType));
    }

    private Menu backToSettings(AppContext appContext) {
        return new SettingsMenu(appContext);
    }
}
