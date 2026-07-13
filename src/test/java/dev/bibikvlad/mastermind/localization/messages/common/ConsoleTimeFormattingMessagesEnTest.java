package dev.bibikvlad.mastermind.localization.messages.common;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleTimeFormattingMessagesEnTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.common.time_formatting",
            LocaleType.ENGLISH.getLocale());
    private final TimeFormattingMessages timeFormattingMessages = new ConsoleTimeFormattingMessages(resourceBundle);

    @Test
    @DisplayName("Returns correct time formatting")
    void testTimeFormatting() {
        String result = "1h 23min 9sec 73ms";

        assertEquals(result, timeFormattingMessages.getHours(1) + " " + timeFormattingMessages.getMinutes(23)
                + " " +timeFormattingMessages.getSeconds(9) + " " + timeFormattingMessages.getMilliseconds(73));
    }
}