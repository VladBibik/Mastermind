package dev.bibikvlad.mastermind.localization.messages.error;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleErrorMessagesEnTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.error.error_messages",
            LocaleType.ENGLISH.getLocale());
    private final ErrorMessages errorMessages = new ConsoleErrorMessages(resourceBundle);

    @Test
    @DisplayName("Returns correct Stats Not Found Message String")
    void testStatsNotFoundMessage() {
        long playerId = 0;
        String result = errorMessages.getStatsNotFound(playerId);

        assertEquals(result, getStatsNotFound());
    }

    @Test
    @DisplayName("Returns correct Last Selected Player Not Found Message String")
    void testLastSelectedPlayerNotFoundMessage() {
        String result = errorMessages.getLastSelectedPlayerNotFound();

        assertEquals(result, getLastSelectedPlayerNotFound());
    }

    String getStatsNotFound() {
        return "❌ No statistics found for player with ID 0.";
    }

    String getLastSelectedPlayerNotFound() {
        return "❌ Last selected player could not be found.";
    }
}
