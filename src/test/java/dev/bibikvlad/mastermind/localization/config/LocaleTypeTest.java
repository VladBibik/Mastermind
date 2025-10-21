package dev.bibikvlad.mastermind.localization.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocaleTypeTest {
    @Test
    @DisplayName("Returns correct locale type from locale index")
    void fromLocaleIndexTest() {
        assertEquals(LocaleType.ENGLISH, LocaleType.fromLocaleIndex(1));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLocaleIndex(2));
    }

    @Test
    @DisplayName("Returns correct locale type from language string")
    void fromLanguageStringTest() {
        assertEquals(LocaleType.ENGLISH, LocaleType.fromLanguageString("English"));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLanguageString("Russian"));

        assertEquals(LocaleType.ENGLISH, LocaleType.fromLanguageString("ENGLISH"));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLanguageString("RUSSIAN"));

        assertEquals(LocaleType.ENGLISH, LocaleType.fromLanguageString("EnGlIsH"));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLanguageString("rUssIan"));
    }

    @Test
    @DisplayName("Returns correct locale type from locale string")
    void fromLocaleStringTest() {
        assertEquals(LocaleType.ENGLISH, LocaleType.fromLocaleString("en"));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLocaleString("ru"));

        assertEquals(LocaleType.ENGLISH, LocaleType.fromLocaleString("EN"));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLocaleString("RU"));
    }

    @Test
    @DisplayName("Throws exception on negative locale index")
    void negativeLocaleIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LocaleType.fromLocaleIndex(-1));
    }

    @Test
    @DisplayName("Throws exception on invalid locale index")
    void invalidLocaleIndexThrowsException() {
        for (int i = 3; i < 100; i++) {
            int index = i;

            assertThrows(IllegalArgumentException.class, () -> LocaleType.fromLocaleIndex(index));
        }
    }
}
