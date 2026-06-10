package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

public class PlayerCreationInputInterpreter {
    public static PlayerCreationInput readSelection(Parser parser) {
        String userInput = parser.parseUserInput();

        //TODO: Think if we should use trim and toLowerCase here!
        if (GlobalMenuCommands.EXIT.contains(userInput.trim().toLowerCase())) {
            return new PlayerCreationInput(true, userInput);
        } else {
            return new PlayerCreationInput(false, userInput);
        }
    }
}
