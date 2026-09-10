package dev.bibikvlad.mastermind.input.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StringEmptyValidatorTest {
    @Test
    @DisplayName("Whitespace-only string is considered empty")
    void whitespaceOnlyStringIsConsideredEmpty() {
        assertTrue(StringEmptyValidator.isNullOrEmpty("   \t\n"));
    }
}
