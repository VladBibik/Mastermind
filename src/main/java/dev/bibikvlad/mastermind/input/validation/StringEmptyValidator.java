package dev.bibikvlad.mastermind.input.validation;

public class StringEmptyValidator {
    private StringEmptyValidator() {
        throw new AssertionError("StringEmptyValidator cannot be instantiated");
    }

    public static boolean isNullOrEmpty(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }
}
