package dev.bibikvlad.mastermind.validators;

import dev.bibikvlad.mastermind.model.enums.MastermindColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameInputValidatorTest {
    private static final int COMBINATION_LENGTH = 4;

    @Test
    @DisplayName("Accepts all 1296 valid 4-symbol combinations")
    void acceptsAllValidSymbolCombinations() {
        char[] correctInputs = new char[MastermindColors.values().length];
        for (int i = 0; i < correctInputs.length; i++) {
            correctInputs[i] = MastermindColors.fromColorIndex(i).getSymbol();
        }

        char[] input = new char[COMBINATION_LENGTH];

        validateAllCombinations(0, correctInputs, input);
    }

    private void validateAllCombinations(int index, char[] correctInputs, char[] input) {
        if (index >= input.length) {
            assertTrue(GameInputValidator.isInputValid(String.valueOf(input)),
                    "Invalid input: " + String.valueOf(input));

            return;
        }

        for (char colorSymbol : correctInputs) {
            input[index] = colorSymbol;

            validateAllCombinations(index + 1, correctInputs, input);
        }
    }
}
