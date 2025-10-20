package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class LogoColorSelectionMenu {
    private final Player currentPlayer;
    private final PlayerService playerService;
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final LogoMessages logoMessages;

    public LogoColorSelectionMenu(Player currentPlayer, PlayerService playerService,
                                  LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.currentPlayer = currentPlayer;
        this.playerService = playerService;
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.logoMessages = localizationContext.getLogoMessages();
    }

    public LogoColorsBundle selectLogoColors() {
        return new LogoColorsBundle(
                selectLogoBorderColor(),
                selectLogoBorderColor(),
                selectLogoBorderColor(),
                selectLogoBorderColor()
        );
    }

    private ConsoleColor selectLogoBorderColor() {
        System.out.println(getDefaultTipMessage());
        System.out.println(getDefaultGoBackMessage());
        System.out.println("Please select border color");

        printForegroundColorChoices();

        return colorSelectionLoop();
    }

    private ConsoleColor colorSelectionLoop() {
        while (true) {
            String userInput = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                continue;
            }

            try {
                int userInputNumber = Integer.parseInt(userInput);

                try {
                    return ConsoleColor.getForegroundColorByIndex(userInputNumber);
                } catch (IllegalArgumentException exception) {
                    System.out.println("Invalid input. Please enter a number corresponding " +
                            "to the desired color option.");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");
            }
        }
    }

    private void printForegroundColorChoices() {
        for (ConsoleColor color : ConsoleColor.getForegroundColors()) {
            System.out.println(logoMessages.getColor(color.getLocalizationKey()));
        }
    }

    private void printBackgroundColorChoices() {
        for (ConsoleColor color : ConsoleColor.getBackgroundColors()) {
            System.out.println(logoMessages.getColor(color.getLocalizationKey()));
        }
    }

    private String getDefaultTipMessage() {
        return "To select a color please enter a corresponding number or print color name";
    }

    private String getDefaultGoBackMessage() {
        return "To return to previous menu print 'close', or 'exit'";
    }
}
