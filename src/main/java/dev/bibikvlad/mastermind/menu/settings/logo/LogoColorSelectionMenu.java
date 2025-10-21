package dev.bibikvlad.mastermind.menu.settings.logo;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.validators.StringEmptyValidator;
import dev.bibikvlad.utils.DefaultLogoColorsBundle;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

public class LogoColorSelectionMenu {
    private final Player currentPlayer;
    private final PlayerService playerService;
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final LogoMessages logoMessages;
    private final ColorSelectionMenu colorSelectionMenu;

    private LogoColorsBundle logoColorsBundle;
    private boolean isDone = false;

    public LogoColorSelectionMenu(Player currentPlayer, PlayerService playerService,
                                  LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.currentPlayer = currentPlayer;
        this.playerService = playerService;
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.logoMessages = localizationContext.getLogoMessages();
        this.colorSelectionMenu = new ColorSelectionMenu(localizationContext, parser);

        this.logoColorsBundle = currentPlayer.getPlayerConfig().getLogoColorsBundle();
    }

    public void selectLogoColors() {
        while (!isDone) {

            displayMenu();

            String userInput = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                System.out.println("Input cannot be empty. Please try again.");

                continue;
            }

            int userInputNumber;

            try {
                userInputNumber = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                continue;
            }

            menuOptionSwitcher(userInputNumber);
        }
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Print current logo");
        System.out.println("2. Change border color");
        System.out.println("3. Change main color");
        System.out.println("4. Change accent color");
        System.out.println("5. Change background color");
        System.out.println("6. Reset logo colors to default");
        System.out.println("7. Return back to settings");
    }

    private void menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> printCurrentLogo();
            case 2 -> changeBorderColor();
            case 3 -> changeMainColor();
            case 4 -> changeAccentColor();
            case 5 -> changeBackgroundColor();
            case 6 -> resetToDefault();
            case 7 -> saveAndReturnBack();
            default -> System.out.println("Invalid selection. Please enter a number corresponding to the menu option.");
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
                logoColorsBundle.getLogoMainColor(),
                logoColorsBundle.getLogoAccentColor(),
                logoColorsBundle.getLogoBackgroundColor());
    }

    private void changeMainColor() {
        ConsoleColor mainColor = colorSelectionMenu.selectForegroundColor();

        if (mainColor == null) {
            return;
        }

        logoColorsBundle = new LogoColorsBundle(
                logoColorsBundle.getLogoBorderColor(),
                mainColor,
                logoColorsBundle.getLogoAccentColor(),
                logoColorsBundle.getLogoBackgroundColor());
    }

    private void changeAccentColor() {
        ConsoleColor accentColor = colorSelectionMenu.selectForegroundColor();

        if (accentColor == null) {
            return;
        }

        logoColorsBundle = new LogoColorsBundle(
                logoColorsBundle.getLogoBorderColor(),
                logoColorsBundle.getLogoMainColor(),
                accentColor,
                logoColorsBundle.getLogoBackgroundColor());
    }

    private void changeBackgroundColor() {
        ConsoleColor backgroundColor = colorSelectionMenu.selectBackgroundColor();

        if (backgroundColor == null) {
            return;
        }

        logoColorsBundle = new LogoColorsBundle(
                logoColorsBundle.getLogoBorderColor(),
                logoColorsBundle.getLogoMainColor(),
                logoColorsBundle.getLogoAccentColor(),
                backgroundColor);
    }

    private void resetToDefault() {
        logoColorsBundle = DefaultLogoColorsBundle.get();
    }

    private void saveAndReturnBack() {
        playerService.updateLogoColors(currentPlayer.getId(), logoColorsBundle);

        isDone = true;
    }
}
