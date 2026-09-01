package dev.bibikvlad.mastermind.menu.main.settings.language;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.context.AppContextFactory;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.language.LanguageSelectionMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.settings.SettingsMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class LanguageSelectionMenu extends Menu {
    private final PlayerService playerService;
    private final Printer printer;
    private final Parser parser;
    private final Player currentPlayer;
    private final InteractionMessages interactionMessages;
    private final LanguageSelectionMessages languageSelectionMessages;

    private boolean shouldRenderMenu = true;

    public LanguageSelectionMenu(AppContext appContext) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.currentPlayer = appContext.currentPlayer();
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.languageSelectionMessages = appContext.localizationContext().getMessages(MessageType.LANGUAGE_MENU);
    }

    @Override
    public Menu run() {
        if (shouldRenderMenu) {
            warnIfCurrentlyInCompatibilityMode();

            printMenuOptions();

            shouldRenderMenu = false;
        }

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::selectLocaleByIndex)
                .orElse(new SettingsMenu(appContext));
    }

    private void printMenuOptions() {
        printer.printMessage(languageSelectionMessages.getMenuOptions());

        LocalizationType[] locales = LocalizationType.values();

        for (int i = 0; i < locales.length; i++) {
            String languageOption = (i + 1) + ". " + locales[i].getNativeDisplayName();

            printer.printMessage(languageOption);
        }
    }

    private Menu selectLocaleByIndex(int userInputIndex) {
        try {
            return checkLanguageSelection(LocalizationType.fromIndex(userInputIndex));
        } catch (IllegalArgumentException _) {
            printer.printMessage(interactionMessages.getInvalidInput());

            return this;
        }
    }

    private Menu checkLanguageSelection(LocalizationType localizationType) {
        LocalizationType currentLocalizationType = currentPlayer.getPlayerConfig().localizationType();

        if (localizationType.equals(currentLocalizationType)) {
            printer.printMessage(languageSelectionMessages.getAlreadySelected());

            return this;
        } else {
            warnIfCompatibilityModeSelected(localizationType);

            return applyLanguageChange(localizationType);
        }
    }

    private Menu applyLanguageChange(LocalizationType localizationType) {
        AppContext newAppContext = AppContextFactory.withLocale(this.appContext, localizationType);

        updatePlayerLocale(localizationType);
        printLanguageChangeConfirmation(newAppContext, localizationType);

        return new SettingsMenu(newAppContext);
    }

    private void updatePlayerLocale(LocalizationType localizationType) {
        playerService.updatePlayerLocale(currentPlayer.getId(), localizationType);
    }

    private void printLanguageChangeConfirmation(AppContext newAppContext, LocalizationType localizationType) {
        LanguageSelectionMessages messages = newAppContext.localizationContext().getMessages(MessageType.LANGUAGE_MENU);

        printer.printMessage(messages.getLanguageChanged(localizationType.getNativeDisplayName()));
        printer.printMessage(messages.getBackToSettings());

        parser.parse();
    }

    private void warnIfCurrentlyInCompatibilityMode() {
        LocalizationType localizationType = currentPlayer.getPlayerConfig().localizationType();

        if (playerService.isInCompatibilityMode(localizationType)) {
            printer.printMessage("TEMP MESSAGE");

            waitForUserConfirmation();
        }
    }

    private void warnIfCompatibilityModeSelected(LocalizationType localizationType) {
        if (playerService.isInCompatibilityMode(localizationType)) {
            printer.printMessage("TEMP MESSAGE");

            waitForUserConfirmation();
        }
    }

    private void waitForUserConfirmation() {
        printer.printMessage(interactionMessages.getPressEnter());

        parser.parse();
    }
}
