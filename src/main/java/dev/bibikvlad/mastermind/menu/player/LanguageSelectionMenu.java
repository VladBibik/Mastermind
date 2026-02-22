package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.bootstrap.AppContextFactory;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
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
                .orElse(this);
    }

    private void printMenuOptions() {
        printer.printMessage("""
                Please select a language
                Enter a number corresponding to desired language
                
                1. English
                2. Russian
                """);
    }

    private Menu selectLocaleByIndex(int userInputIndex) {
        try {
            return checkLanguageSelection(LocaleType.fromLocaleIndex(userInputIndex));
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
            printer.printMessage("Language changed to: " + localeType.getLanguageName());

            return updateLanguage(localeType);
        }
    }

    private Menu updateLanguage(LocaleType localeType) {
        playerService.updatePlayerLocale(appContext.currentPlayer().getId(), localeType);

        return createNewContext(localeType);
    }

    private Menu createNewContext(LocaleType localeType) {
        AppContextFactory appContextFactory = new AppContextFactory(appContext);

        return backToSettings(appContextFactory.recreateWithLocale(localeType));
    }

    private Menu backToSettings(AppContext appContext) {
        return new SettingsMenu(appContext);
    }
}
