package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.LanguageSelectionMenu;
import dev.bibikvlad.mastermind.menu.settings.logo.LogoColorSelectionMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class SettingsMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;

    private boolean isDone = false;

    public SettingsMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                        PlayerService playerService, Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = currentPlayer;
    }

    public void menu() {
        while (!isDone) {
            displayMenu();

            Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

            if (selection.isEmpty())
                break;

            menuOptionSwitcher(selection.get());
        }
    }

    private void displayMenu() {
        System.out.println("1. Change language");
        System.out.println("2. Change logo colors");
        System.out.println("3. Back to the main menu");
    }

    private void menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> changeLanguage();
            case 2 -> changeLogoColor();
            default -> System.out.println("Invalid selection. Please enter a number corresponding to the menu option.");
        }
    }

    private void changeLanguage() {
        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        playerService.updatePlayerLocale(currentPlayer.getId(), localeType);

        localizationContext.changeLocale(localeType);

        System.out.println("Language changed successfully to " + localeType.getLanguageName() + "\n");
    }

    private void changeLogoColor() {
        LogoColorSelectionMenu menu =
                new LogoColorSelectionMenu(currentPlayer, playerService, localizationContext, parser);

        menu.selectLogoColors();
    }
}
