package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.validators.StringEmptyValidator;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

public class LogoColorSelectionMenu {
    private final Player currentPlayer;
    private final PlayerService playerService;
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final LogoMessages logoMessages;

    private LogoColorsBundle logoColorsBundle;

    public LogoColorSelectionMenu(Player currentPlayer, PlayerService playerService,
                                  LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.currentPlayer = currentPlayer;
        this.playerService = playerService;
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.logoMessages = localizationContext.getLogoMessages();

        this.logoColorsBundle = currentPlayer.getPlayerConfig().getLogoColorsBundle();
    }

    public void selectLogoColors() {
        while (true) {
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
        System.out.println("6. Return back to settings");
    }

    private void menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> printCurrentLogo();
            default -> System.out.println("Invalid selection. Please enter a number corresponding to the menu option.");
        }
    }

    private void printCurrentLogo() {
        System.out.println(ColoredAsciiLogo.getLogo(logoColorsBundle));
    }
}
