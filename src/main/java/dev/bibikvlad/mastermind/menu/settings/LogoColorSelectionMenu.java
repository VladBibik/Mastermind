package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.validators.StringEmptyValidator;

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

            try {
                int userInputNumber = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                continue;
            }
        }
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Change border color");
        System.out.println("2. Change main color");
        System.out.println("3. Change accent color");
        System.out.println("4. Change background color");
        System.out.println("5. Return back to settings");
    }
}
