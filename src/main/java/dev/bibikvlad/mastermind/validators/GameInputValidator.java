package dev.bibikvlad.mastermind.validators;

import java.util.regex.Pattern;

public class GameInputValidator {
    private static final Pattern VALID_INPUT_PATTERN = Pattern.compile("[rgybpw]{4}");

    public static boolean isInputValid(String userInput) {
        return VALID_INPUT_PATTERN.matcher(userInput).matches();
    }
}
