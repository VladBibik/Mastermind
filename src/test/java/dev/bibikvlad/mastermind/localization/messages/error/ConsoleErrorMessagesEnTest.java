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
    @DisplayName("Returns correct Invalid Input Message String")
    void testStatsNotFoundMessage() {
        long playerId = 0;
        String result = errorMessages.getStatsNotFound(playerId);

        assertEquals(result, getStatsNotFound());
    }

    String getStatsNotFound() {
        return "❌ No statistics found for player with ID 0.";
    }
}
