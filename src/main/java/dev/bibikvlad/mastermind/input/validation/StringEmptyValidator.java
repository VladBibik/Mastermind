package dev.bibikvlad.mastermind.input.validation;

public class StringEmptyValidator {
    public static boolean isNullOrEmpty(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }
}
