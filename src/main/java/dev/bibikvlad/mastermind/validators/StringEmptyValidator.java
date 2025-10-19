package dev.bibikvlad.mastermind.validators;

public class StringEmptyValidator {
    public static boolean isNullOrEmpty(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }
}
