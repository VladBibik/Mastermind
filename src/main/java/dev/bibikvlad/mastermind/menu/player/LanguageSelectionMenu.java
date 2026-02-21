package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
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

        if (selection.isPresent()) {
            LocaleType localeType = selectLocaleByIndex(selection.get());

            if (localeType == null) {
                printer.printMessage("❌ Invalid input. Please enter a number corresponding to the menu option");

                return this;
            }

            if (localeType.equals(appContext.currentPlayer().getPlayerConfig().locale())) {
                printer.printMessage("Language is already selected.");

                return this;
            }

            printer.printMessage("Language changed to: " + localeType.getLanguageName());

            playerService.updatePlayerLocale(appContext.currentPlayer().getId(), localeType);

            Player updatedPlayer = appContext.currentPlayer().withLocale(localeType);
            LocalizationContext newLocalizationContext = new LocalizationContext(localeType);
            AppContext newAppContext = new AppContext(newLocalizationContext, this.appContext.services(),
                    this.appContext.printer(), this.appContext.parser(), updatedPlayer);

            return new SettingsMenu(newAppContext);
        } else {
            printer.printMessage("❌ Invalid input. Please enter a number corresponding to the menu option");

            return this;
        }
    }

    private void printMenuOptions() {
        printer.printMessage("""
                Please select a language
                Enter a number corresponding to desired language
                
                1. English
                2. Russian
                """);
    }

    private LocaleType selectLocaleByIndex(int userInputIndex) {
        try {
            return LocaleType.fromLocaleIndex(userInputIndex);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
