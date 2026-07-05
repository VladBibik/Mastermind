package dev.bibikvlad.mastermind.localization.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocaleTypeTest {
    @Test
    @DisplayName("Returns correct locale type from locale index")
    void fromLocaleIndexTest() {
        assertEquals(LocaleType.ENGLISH, LocaleType.fromIndex(1));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromIndex(2));
    }

    @Test
    @DisplayName("Throws exception on negative locale index")
    void negativeLocaleIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LocaleType.fromIndex(-1));
    }

    @Test
    @DisplayName("Throws exception on invalid locale index")
    void invalidLocaleIndexThrowsException() {
        for (int i = 3; i < 100; i++) {
            int index = i;

            assertThrows(IllegalArgumentException.class, () -> LocaleType.fromIndex(index));
        }
    }
}
