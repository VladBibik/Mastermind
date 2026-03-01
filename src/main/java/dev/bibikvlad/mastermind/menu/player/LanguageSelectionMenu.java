package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.context.AppContextFactory;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.error.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.language.LanguageSelectionMessages;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class LanguageSelectionMenu extends Menu {
    private final PlayerService playerService;
    private final Printer printer;
    private final Parser parser;
    private final InteractionMessages interactionMessages;
    private final LanguageSelectionMessages languageSelectionMessages;

    private boolean showMenuOnNextLoop = true;

    public LanguageSelectionMenu(AppContext appContext) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.languageSelectionMessages = appContext.localizationContext().getMessages(MessageType.LANGUAGE_MENU);
    }

    @Override
    public Menu run() {
        if (showMenuOnNextLoop) {
            printMenuOptions();

            showMenuOnNextLoop = false;
        }

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::selectLocaleByIndex)
                .orElse(new SettingsMenu(appContext));
    }

    private void printMenuOptions() {
        printer.printMessage(languageSelectionMessages.getMenuOptions());

        LocaleType[] locales = LocaleType.values();

        for (int i = 0; i < locales.length; i++) {
            String languageOption = (i + 1) + ". " + locales[i].getNativeDisplayName();

            printer.printMessage(languageOption);
        }
    }

    private Menu selectLocaleByIndex(int userInputIndex) {
        try {
            return checkLanguageSelection(LocaleType.fromIndex(userInputIndex));
        } catch (IllegalArgumentException exception) {
            printer.printMessage(interactionMessages.getInvalidInputMessage());

            return this;
        }
    }

    private Menu checkLanguageSelection(LocaleType localeType) {
        if (localeType.equals(appContext.currentPlayer().getPlayerConfig().locale())) {
            printer.printMessage(languageSelectionMessages.getAlreadySelected());

            return this;
        } else {
            return applyLanguageChange(localeType);
        }
    }

    private Menu applyLanguageChange(LocaleType localeType) {
        AppContext newAppContext = AppContextFactory.withLocale(this.appContext, localeType);

        updateLanguage(localeType);
        printLanguageChangeConfirmation(appContext, localeType);

        return new SettingsMenu(appContext);
    }

    private void updateLanguage(LocaleType localeType) {
        playerService.updatePlayerLocale(appContext.currentPlayer().getId(), localeType);
    }

    private void printLanguageChangeConfirmation(AppContext newAppContext, LocaleType localeType) {
        LanguageSelectionMessages messages = newAppContext.localizationContext().getMessages(MessageType.LANGUAGE_MENU);

        printer.printMessage(messages.getLanguageChanged(localeType.getNativeDisplayName()));
        printer.printMessage(messages.getBackToSettings());

        parser.parseUserInput();
    }
}
