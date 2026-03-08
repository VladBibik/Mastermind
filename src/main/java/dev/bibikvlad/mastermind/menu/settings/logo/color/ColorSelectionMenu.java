package dev.bibikvlad.mastermind.menu.settings.logo.color;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.utils.strings.GameCluesConstants;

import java.util.Optional;
import java.util.function.Function;

public class ColorSelectionMenu {
    private final Printer printer;
    private final Parser parser;
    private final LogoMessages logoMessages;

    public ColorSelectionMenu(AppContext appContext) {
        this.printer = appContext.printer();
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
        printer.printMessage("Enter '0' to return to the Logo Color Selection Menu");

        while (true) {
            Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

            try {
                return selection
                        .map(colorSelector)
                        .orElse(null);
            } catch (IllegalArgumentException exception) {
                printer.printMessage("Invalid input. Please enter a number corresponding to the color option.");
            }
        }
    }

    private void displayForegroundColors() {
        for (ConsoleColor color : ConsoleColor.getForegroundColors()) {
            String foregroundColor = String.format("%2d: %-23s %s%s%s",
                    color.getIndex(),
                    logoMessages.getColor(color.getLocalizationKey()),
                    color.getCode(),
                    GameCluesConstants.CIRCLE_SOLID,
                    ConsoleColor.RESET.getCode());

            printer.printMessage(foregroundColor);
        }
    }

    private void displayBackgroundColors() {
        for (ConsoleColor color : ConsoleColor.getBackgroundColors()) {
            String backgroundColor = String.format("%d: %-18s %s%s%s",
                    color.getIndex(),
                    logoMessages.getColor(color.getLocalizationKey()),
                    color.getCode(),
                    "    ",
                    ConsoleColor.RESET.getCode());

            printer.printMessage(backgroundColor);
        }
    }
}