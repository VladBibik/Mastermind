package dev.bibikvlad.mastermind.validators;

import dev.bibikvlad.mastermind.model.enums.MastermindColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameInputValidatorTest {
    @Test
    @DisplayName("Accepts all 1296 valid 4-symbol combinations")
    void testAllPossibleCorrectInputs() {
        char[] correctInputs = new char[MastermindColors.values().length];
        for (int i = 0; i < correctInputs.length; i++) {
            correctInputs[i] = MastermindColors.fromColorIndex(i).getSymbol();
        }

        char[] input = new char[4];

        generate(0, correctInputs, input);
    }

    private void generate(int index, char[] correctInputs, char[] input) {
        if (index >= input.length) {
            assertTrue(GameInputValidator.isInputValid(String.valueOf(input)));

            return;
        }

        for (char colorSymbol : correctInputs) {
            input[index] = colorSymbol;

            generate(index + 1, correctInputs, input);
        }
    }
}
