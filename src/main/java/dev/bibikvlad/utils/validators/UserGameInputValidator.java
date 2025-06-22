package dev.bibikvlad.utils.validators;

import java.util.regex.Pattern;

public class UserGameInputValidator {
    private static final Pattern validInputPattern = Pattern.compile("[rgybpw]{4}");

    public static boolean isInputValid(String userInput) {
        return validInputPattern.matcher(userInput).matches();
    }
}
