package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

import java.util.Optional;

public class IntegerInputInterpreter {
    public static Optional<Integer> readSelection(Parser parser) {
        String userInput = parser.parseUserInput().trim().toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(userInput)) {
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(userInput));
        } catch (NumberFormatException exception) {
            return Optional.of(-1);
        }
    }
}
