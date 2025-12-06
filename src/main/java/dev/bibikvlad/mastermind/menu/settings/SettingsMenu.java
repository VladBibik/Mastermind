package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.LanguageSelectionMenu;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.settings.logo.LogoColorSelectionMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class SettingsMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    private Player currentPlayer;

    public SettingsMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                        PlayerService playerService) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
    }

    //TODO: Should we go back on 'close', and 'exit'?
    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        if (selection.isEmpty())
            return new MainMenu(localizationContext, parser, playerService);

        return menuOptionSwitcher(selection.get());
    }

    private void displayMenu() {
        System.out.println("1. Change language");
        System.out.println("2. Change logo colors");
        System.out.println("3. Back to the main menu");
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return changeLanguage();
            }
            case 2 -> {
                return changeLogoColor();
            }
            case 3 -> {
                return exit();
            }
            default -> {
                System.out.println("Invalid selection. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    //TODO: Is there a better way to do this?
    private Menu changeLanguage() {
        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        if (!localeType.equals(currentPlayer.getPlayerConfig().getLocale())) {
            currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                    () -> new IllegalStateException("No last selected player found!"));
        }

        return this;
    }

    private Menu changeLogoColor() {
        return new LogoColorSelectionMenu(localizationContext, parser, playerService);
    }

    private Menu exit() {
        return new MainMenu(localizationContext, parser, playerService);
    }
}
