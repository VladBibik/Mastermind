package dev.bibikvlad.mastermind.menu.main.settings.logo.color;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.LogoColorSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.color.ColorMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.utils.strings.GameCluesConstants;

import java.util.Optional;
import java.util.function.Function;

public class ColorSelectionMenu {
    private final Printer printer;
    private final Parser parser;
    private final LogoColorSelectionMessages logoMessages;
    private final ColorMessages colorMessages;
    private final InteractionMessages interactionMessages;

    public ColorSelectionMenu(AppContext appContext) {
        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.logoMessages = appContext.localizationContext().getMessages(MessageType.LOGO);
        this.colorMessages = appContext.localizationContext().getMessages(MessageType.COLOR);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
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
        printer.printMessage(logoMessages.getColorReturn());

        while (true) {
            Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

            try {
                return selection
                        .map(colorSelector)
                        .orElse(null);
            } catch (IllegalArgumentException exception) {
                printer.printMessage(interactionMessages.getInvalidInputMessage());
            }
        }
    }

    private void displayForegroundColors() {
        for (ConsoleColor color : ConsoleColor.getForegroundColors()) {
            printer.printMessage(foregroundColorFormatter(color));
        }
    }

    private String foregroundColorFormatter(ConsoleColor color) {
        return String.format("%2d: %-23s %s%s%s",
                color.getIndex(),
                colorMessages.getColor(color.getLocalizationKey()),
                color.getCode(),
                GameCluesConstants.CIRCLE_SOLID,
                ConsoleColor.RESET.getCode());
    }

    private void displayBackgroundColors() {
        for (ConsoleColor color : ConsoleColor.getBackgroundColors()) {
            printer.printMessage(backgroundColorFormatter(color));
        }
    }

    private String backgroundColorFormatter(ConsoleColor color) {
        return String.format("%d: %-18s %s%s%s",
                color.getIndex(),
                colorMessages.getColor(color.getLocalizationKey()),
                color.getCode(),
                "    ",
                ConsoleColor.RESET.getCode());
    }
}