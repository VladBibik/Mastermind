package dev.bibikvlad.mastermind.menu.settings.logo;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.validators.StringEmptyValidator;

public class ColorSelectionMenu {
    private final MastermindUserInputParser parser;
    private final LogoMessages logoMessages;

    public ColorSelectionMenu(LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.parser = parser;
        this.logoMessages = localizationContext.getLogoMessages();
    }

    public ConsoleColor selectForegroundColor() {
        displayForegroundColors();
        System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

        while (true) {
            String userInput = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                System.out.println("Input cannot be empty. Please try again.");

                continue;
            }

            if (userInput.equals("exit") || userInput.equals("quit")) {
                return null;
            }

            int userInputNumber;

            try {
                userInputNumber = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the color option.");

                continue;
            }

            try {
                return ConsoleColor.getForegroundColorByIndex(userInputNumber);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the color option.");
            }
        }
    }

    public ConsoleColor selectBackgroundColor() {
        displayBackgroundColors();
        System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

        while (true) {
            String userInput = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                System.out.println("Input cannot be empty. Please try again.");

                continue;
            }

            if (userInput.equals("exit") || userInput.equals("quit")) {
                return null;
            }

            int userInputNumber;

            try {
                userInputNumber = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the color option.");

                continue;
            }

            try {
                return ConsoleColor.getForegroundColorByIndex(userInputNumber);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the color option.");
            }
        }
    }

    private void displayForegroundColors() {
        for (ConsoleColor color : ConsoleColor.getForegroundColors()) {
            System.out.println(color.getIndex() + ": " + logoMessages.getColor(color.getLocalizationKey()));
        }
    }

    private void displayBackgroundColors() {
        for (ConsoleColor color : ConsoleColor.getBackgroundColors()) {
            System.out.println(color.getIndex() + ": " + logoMessages.getColor(color.getLocalizationKey()));
        }
    }
}