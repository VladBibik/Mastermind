package dev.bibikvlad.mastermind.menu.main.profile;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.GlobalMenuCommands;
import dev.bibikvlad.mastermind.input.interpreter.PlayerNameInput;
import dev.bibikvlad.mastermind.input.interpreter.PlayerNameInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;

import java.util.Optional;

public class PlayerNameReader {
    private final Parser parser;
    private final Printer printer;
    private final NewPlayerCreationMenuMessages messages;

    public PlayerNameReader(Parser parser, Printer printer, NewPlayerCreationMenuMessages messages) {
        this.parser = parser;
        this.printer = printer;
        this.messages = messages;
    }

    public Optional<String> readPlayerName() {
        PlayerNameInput input = PlayerNameInputInterpreter.readSelection(parser);

        if (input.isExit()) {
            printer.printMessage(messages.getReservedCommandConfirmation(input.userInput()));

            String confirmation = parser.parseUserInput().trim().toLowerCase();

            if (!GlobalMenuCommands.YES.contains(confirmation)) {
                return Optional.empty();
            }
        }

        return Optional.of(input.userInput());
    }
}
