package dev.bibikvlad.mastermind.localization.configurations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocaleTypeTest {
    @Test
    @DisplayName("Returns correct locale type from locale index")
    void fromLocaleIndexTest() {
        assertEquals(LocaleType.ENGLISH, LocaleType.fromLocaleIndex(0));
        assertEquals(LocaleType.RUSSIAN, LocaleType.fromLocaleIndex(1));
    }
}
