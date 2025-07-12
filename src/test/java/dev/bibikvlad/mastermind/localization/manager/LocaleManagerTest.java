package dev.bibikvlad.mastermind.localization.manager;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.configurations.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocaleManagerTest {
    @Test
    @DisplayName("Returns English locale")
    void testEnglishLocaleType() {
        LocaleManager localeManager = new LocaleManager(LocaleType.ENGLISH);
        GameMessagesLocale gameMessagesLocale = localeManager.getGameMessagesLocale();
        String answer = "rrrr";

        assertEquals("Game Over! The solution was: "
                        + InputVisualRepresentation.getVisualRepresentation(answer),
                gameMessagesLocale.getGameOverMessage(answer));
    }

    @Test
    @DisplayName("Returns Russian locale")
    void testRussianLocaleType() {
        LocaleManager localeManager = new LocaleManager(LocaleType.RUSSIAN);
        GameMessagesLocale gameMessagesLocale = localeManager.getGameMessagesLocale();
        String answer = "rrrr";

        assertEquals("Game Over! Ответом была комбинация: "
                        + InputVisualRepresentation.getVisualRepresentation(answer),
                gameMessagesLocale.getGameOverMessage(answer));
    }
}
