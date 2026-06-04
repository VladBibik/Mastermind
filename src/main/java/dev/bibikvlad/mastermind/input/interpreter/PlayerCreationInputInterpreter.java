package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

import java.util.Optional;

public class PlayerCreationInputInterpreter {
    public static Optional<String> readSelection(Parser parser) {
        String userInput = parser.parseUserInput().trim().toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(userInput)) {
            return Optional.empty();
        } else {
            return Optional.of(userInput);
        }
    }
}
