package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;

import java.util.Optional;
import java.util.Set;

public class MainMenuInputInterpreter {
    private static final Set<String> playSet = Set.of(
            "play",
            "",
            "start",
            "go",
            "mastermind",
            "1"
    );
    private static final Set<String> exitSet = Set.of(
            "exit",
            "close",
            "quit",
            "back",
            "q",
            "0"
    );

    public static Optional<Integer> readSelection(MastermindUserInputParser parser) {
        String userInput = parser.parseUserInput();

        if (exitSet.contains(userInput.toLowerCase())) {
            return Optional.empty();
        }

        if (playSet.contains(userInput.toLowerCase())) {
            return Optional.of(1);
        }

        try {
            return Optional.of(Integer.parseInt(userInput));
        } catch (NumberFormatException exception) {
            return Optional.of(-1);
        }
    }
}
