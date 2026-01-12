package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.settings.logo.LogoColorSelectionMenu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class SettingsMenu extends Menu {
    private final PlayerService playerService;

    private Player currentPlayer;

    public SettingsMenu(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                        MastermindUserInputParser parser) {
        super(localizationContext, serviceContainer, parser);

        this.playerService = serviceContainer.getPlayerService();
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
    }

    //TODO: Should we go back on 'close', and 'exit'?
    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        if (selection.isEmpty())
            return new MainMenu(localizationContext, serviceContainer, parser);

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

    private Menu changeLanguage() {
        LanguageSelectionMenu languageSelectionMenu = new LanguageSelectionMenu(parser);

        LocaleType localeType = languageSelectionMenu.selectLanguage();

        return checkSelectedLanguage(localeType);
    }

    private Menu checkSelectedLanguage(LocaleType localeType) {
        if (localeType.equals(currentPlayer.getPlayerConfig().locale())) {
            System.out.println("Language is already selected!");

            return this;
        } else {
            playerService.updatePlayerLocale(currentPlayer.getId(), localeType);

            currentPlayer = currentPlayer.withLocale(localeType);

            //TODO:Language name should be localized!
            System.out.println("Language changed to " + localeType.getLanguageName());

            return new SettingsMenu(new LocalizationContext(localeType), serviceContainer, parser);
        }
    }

    private Menu changeLogoColor() {
        return new LogoColorSelectionMenu(localizationContext, serviceContainer, parser);
    }

    private Menu exit() {
        return new MainMenu(localizationContext, serviceContainer, parser);
    }
}
