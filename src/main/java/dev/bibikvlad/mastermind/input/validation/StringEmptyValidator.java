package dev.bibikvlad.mastermind.input.validation;

//TODO: After all menu updates I think this class could be deleted!
public class StringEmptyValidator {
    public static boolean isNullOrEmpty(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }
}
