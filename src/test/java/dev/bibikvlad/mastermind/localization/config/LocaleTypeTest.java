package dev.bibikvlad.mastermind.localization.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocaleTypeTest {
    @Test
    @DisplayName("Returns correct locale type from locale index")
    void fromLocaleIndexTest() {
        assertEquals(LocalizationType.ENGLISH, LocalizationType.fromIndex(1));
        assertEquals(LocalizationType.RUSSIAN, LocalizationType.fromIndex(2));
        assertEquals(LocalizationType.COMPATIBILITY, LocalizationType.fromIndex(3));
    }

    @Test
    @DisplayName("Throws exception on negative locale index")
    void negativeLocaleIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LocalizationType.fromIndex(-1));
    }

    @Test
    @DisplayName("Throws exception on invalid locale index")
    void invalidLocaleIndexThrowsException() {
        for (int i = 4; i < 100; i++) {
            int index = i;

            assertThrows(IllegalArgumentException.class, () -> LocalizationType.fromIndex(index));
        }
    }
}
