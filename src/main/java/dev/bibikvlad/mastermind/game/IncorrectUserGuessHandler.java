package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;

public class IncorrectUserGuessHandler {
    public static void handle(String answer, String userInput) {
        System.out.println(InputVisualRepresentation.getVisualRepresentation(userInput)
                + "        "
                + ClueGenerator.generate(answer, userInput));
    }
}
