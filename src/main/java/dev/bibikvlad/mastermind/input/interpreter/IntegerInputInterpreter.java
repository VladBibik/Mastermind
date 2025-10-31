package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;

import java.util.Optional;

public class IntegerInputInterpreter {
    public static Optional<Integer> readSelection(MastermindUserInputParser parser) {
        String userInput = parser.parseUserInput();

        if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("close")) {
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(userInput));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

            return Optional.of(-1);
        }
    }
}
