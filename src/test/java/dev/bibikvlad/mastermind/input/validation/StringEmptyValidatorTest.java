package dev.bibikvlad.mastermind.input.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringEmptyValidatorTest {
    @Test
    @DisplayName("Whitespace-only string is considered empty")
    void whitespaceOnlyStringIsConsideredEmpty() {
        assertTrue(StringEmptyValidator.isNullOrEmpty("   \t\n"));
    }

    @Test
    @DisplayName("Null name returns false")
    void nullNameReturnsFalse() {
        assertTrue(StringEmptyValidator.isNullOrEmpty(null));
    }

    @Test
    @DisplayName("Non-empty string is not considered empty")
    void nonEmptyStringIsNotConsideredEmpty() {
        assertFalse(StringEmptyValidator.isNullOrEmpty("TestName"));
    }

    @Test
    @DisplayName("String with surrounding whitespace is not considered empty")
    void stringWithSurroundingWhitespaceIsNotConsideredEmpty() {
        assertTrue(StringEmptyValidator.isNullOrEmpty("  TestName  "));
    }
}
