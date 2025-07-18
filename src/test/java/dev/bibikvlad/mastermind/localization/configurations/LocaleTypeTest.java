package dev.bibikvlad.mastermind.localization.configurations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocaleTypeTest {
    @Test
    @DisplayName("Returns correct locale type from locale index")
    void fromLocaleIndexTest() {
        assertEquals(LocaleType.ENGLISH, LocaleType.fromLocaleIndex(0));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLocaleIndex(1));
    }

    @Test
    @DisplayName("Returns correct locale type from language string")
    void fromLanguageStringTest() {
        assertEquals(LocaleType.ENGLISH, LocaleType.fromLanguageString("English"));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLanguageString("Russian"));
    }

    @Test
    @DisplayName("Throws exception on invalid locale index")
    void invalidLocaleIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LocaleType.fromLocaleIndex(-31));
        assertThrows(IllegalArgumentException.class, () -> LocaleType.fromLocaleIndex(23));
    }
}
