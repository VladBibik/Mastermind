package dev.bibikvlad.mastermind.menu.settings.logo.color;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.utils.strings.GameCluesConstants;

import java.util.function.Function;

public class ColorSelectionMenu {
    private final Parser parser;
    private final LogoMessages logoMessages;

    public ColorSelectionMenu(AppContext appContext) {
        this.parser = appContext.parser();
        this.logoMessages = appContext.localizationContext().getMessages(MessageType.LOGO);
    }

    public ConsoleColor selectForegroundColor() {
        displayForegroundColors();

        return selectColor(ConsoleColor::getForegroundColorByIndex);
    }

    public ConsoleColor selectBackgroundColor() {
        displayBackgroundColors();

        return selectColor(ConsoleColor::getBackgroundColorByIndex);
    }

    private ConsoleColor selectColor(Function<Integer, ConsoleColor> colorSelector) {
        while (true) {

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
                return colorSelector.apply(userInputNumber);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the color option.");
            }
        }
    }

    private void displayForegroundColors() {
        for (ConsoleColor color : ConsoleColor.getForegroundColors()) {
            System.out.println(color.getIndex() + ": " + logoMessages.getColor(color.getLocalizationKey())
                    + " " + color.getCode() + GameCluesConstants.CIRCLE_SOLID + ConsoleColor.RESET.getCode());
        }
    }

    private void displayBackgroundColors() {
        for (ConsoleColor color : ConsoleColor.getBackgroundColors()) {
            System.out.println(color.getIndex() + ": " + logoMessages.getColor(color.getLocalizationKey())
                    + " " + color.getCode() + "    " + ConsoleColor.RESET.getCode());
        }
    }
}