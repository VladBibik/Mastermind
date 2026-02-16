package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

import java.util.Optional;

public class IntegerInputInterpreter {
    public static Optional<Integer> readSelection(Parser parser) {
        String userInput = parser.parseUserInput();

        if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("close")) {
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(userInput));
        } catch (NumberFormatException exception) {
            return Optional.of(-1);
        }
    }
}
