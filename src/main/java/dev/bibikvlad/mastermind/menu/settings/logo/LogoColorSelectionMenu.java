package dev.bibikvlad.mastermind.menu.settings.logo;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.utils.DefaultLogoColorsBundle;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

public class LogoColorSelectionMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;
    private final ColorSelectionMenu colorSelectionMenu;

    private Player currentPlayer;

    private LogoColorsBundle logoColorsBundle;

    public LogoColorSelectionMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                                  PlayerService playerService) {
        this.playerService = playerService;
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.colorSelectionMenu = new ColorSelectionMenu(localizationContext, parser);
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
        this.logoColorsBundle = currentPlayer.getPlayerConfig().logoColorsBundle();
    }

    @Override
    public Menu run() {
        displayMenu();

        String userInput = parser.parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(userInput)) {
            System.out.println("Input cannot be empty. Please try again.");

            return this;
        }

        int userInputNumber;

        try {
            userInputNumber = Integer.parseInt(userInput);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

            return this;
        }

        return menuOptionSwitcher(userInputNumber);
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Print current logo");
        System.out.println("2. Change border color");
        System.out.println("3. Change main color");
        System.out.println("4. Change accent color");
        System.out.println("5. Change background color");
        System.out.println("6. Reset logo colors to default");
        System.out.println("7. Save and return");
        System.out.println("8. Reset changes and return");
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                printCurrentLogo();

                return this;
            }
            case 2 -> {
                changeBorderColor();

                return this;
            }
            case 3 -> {
                changeMainColor();

                return this;
            }
            case 4 -> {
                changeAccentColor();

                return this;
            }
            case 5 -> {
                changeBackgroundColor();

                return this;
            }
            case 6 -> {
                resetToDefault();

                return this;
            }
            case 7 -> {
                return saveAndReturnBack();
            }
            case 8 -> {
                return new SettingsMenu(localizationContext, parser, playerService);
            }
            default -> {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    private void printCurrentLogo() {
        System.out.println(ColoredAsciiLogo.getLogo(logoColorsBundle));
    }

    private void changeBorderColor() {
        ConsoleColor borderColor = colorSelectionMenu.selectForegroundColor();

        if (borderColor == null) {
            return;
        }

        logoColorsBundle = new LogoColorsBundle(
                borderColor,
                logoColorsBundle.logoMainColor(),
                logoColorsBundle.logoAccentColor(),
                logoColorsBundle.logoBackgroundColor());
    }

    private void changeMainColor() {
        ConsoleColor mainColor = colorSelectionMenu.selectForegroundColor();

        if (mainColor == null) {
            return;
        }

        logoColorsBundle = new LogoColorsBundle(
                logoColorsBundle.logoBorderColor(),
                mainColor,
                logoColorsBundle.logoAccentColor(),
                logoColorsBundle.logoBackgroundColor());
    }

    private void changeAccentColor() {
        ConsoleColor accentColor = colorSelectionMenu.selectForegroundColor();

        if (accentColor == null) {
            return;
        }

        logoColorsBundle = new LogoColorsBundle(
                logoColorsBundle.logoBorderColor(),
                logoColorsBundle.logoMainColor(),
                accentColor,
                logoColorsBundle.logoBackgroundColor());
    }

    private void changeBackgroundColor() {
        ConsoleColor backgroundColor = colorSelectionMenu.selectBackgroundColor();

        if (backgroundColor == null) {
            return;
        }

        logoColorsBundle = new LogoColorsBundle(
                logoColorsBundle.logoBorderColor(),
                logoColorsBundle.logoMainColor(),
                logoColorsBundle.logoAccentColor(),
                backgroundColor);
    }

    private void resetToDefault() {
        logoColorsBundle = DefaultLogoColorsBundle.get();
    }

    private Menu saveAndReturnBack() {
        playerService.updateLogoColors(currentPlayer.getId(), logoColorsBundle);

        return new SettingsMenu(localizationContext, parser, playerService);
    }
}
