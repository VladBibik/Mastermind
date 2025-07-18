package dev.bibikvlad.mastermind.localization.factories;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocaleManager;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
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
                getGameMessages(LocaleType.ENGLISH).getGameOverMessage(ANSWER));
    }

    @Test
    @DisplayName("Returns Russian locale")
    void testRussianLocaleType() {
        assertEquals("Game Over! Ответом была комбинация: "
                        + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                getGameMessages(LocaleType.RUSSIAN).getGameOverMessage(ANSWER));
    }

    private GameMessages getGameMessages(LocaleType localeType) {
        LocaleManager localeManager = new LocaleManager(localeType);

        return localeManager.getGameMessages();
    }
}
