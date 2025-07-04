package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.UserInputVisualRepresentation;

public class UserGuessHandler {
    public static boolean handle(String answer, String userInput) {
        if (userInput.equals(answer)) {
            System.out.println("You Won!");

            return true;
        } else {
            System.out.println(UserInputVisualRepresentation.getVisualRepresentation(userInput)
                    + "        "
                    + ClueGenerator.generate(answer, userInput));

            return false;
        }
    }
}
