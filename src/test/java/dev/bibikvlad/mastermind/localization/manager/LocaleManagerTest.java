package dev.bibikvlad.mastermind.localization.manager;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.configurations.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocaleManagerTest {
    private final String ANSWER = "RGBW";

    @Test
    @DisplayName("Returns English locale")
    void testEnglishLocaleType() {
        assertEquals("Game Over! The solution was: "
                        + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                getGameMessagesLocale(LocaleType.ENGLISH).getGameOverMessage(ANSWER));
    }

    @Test
    @DisplayName("Returns Russian locale")
    void testRussianLocaleType() {
        assertEquals("Game Over! Ответом была комбинация: "
                        + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                getGameMessagesLocale(LocaleType.RUSSIAN).getGameOverMessage(ANSWER));
    }

    private GameMessagesLocale getGameMessagesLocale(LocaleType localeType) {
        LocaleManager localeManager = new LocaleManager(localeType);

        return localeManager.getGameMessagesLocale();
    }
}
