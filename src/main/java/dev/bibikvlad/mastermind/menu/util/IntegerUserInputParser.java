package dev.bibikvlad.mastermind.menu.util;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;

import java.util.Optional;

public class IntegerUserInputParser {
    public static Optional<Integer> parse(MastermindUserInputParser parser) {
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
