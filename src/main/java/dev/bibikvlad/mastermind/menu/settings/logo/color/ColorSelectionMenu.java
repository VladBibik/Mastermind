package dev.bibikvlad.mastermind.menu.settings.logo.color;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.utils.strings.GameCluesConstants;

import java.util.List;

public class ColorSelectionMenu {
    private final Parser parser;
    private final LogoMessages logoMessages;

    public ColorSelectionMenu(AppContext appContext) {
        this.parser = appContext.parser();
        this.logoMessages = appContext.localizationContext().getMessages(MessageType.LOGO);
    }

    public ConsoleColor selectForegroundColor() {
        while (true) {
            displayColors(ConsoleColor.getForegroundColors());
            System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

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
        while (true) {
            displayColors(ConsoleColor.getBackgroundColors());
            System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

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
                return ConsoleColor.getBackgroundColorByIndex(userInputNumber);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the color option.");
            }
        }
    }

    private void displayColors(List<ConsoleColor> colors) {
        for (ConsoleColor color : colors) {
            System.out.println(color.getIndex() + ": " + logoMessages.getColor(color.getLocalizationKey())
                    + " " + color.getCode() + GameCluesConstants.CIRCLE_SOLID + ConsoleColor.RESET.getCode());
        }
    }
}