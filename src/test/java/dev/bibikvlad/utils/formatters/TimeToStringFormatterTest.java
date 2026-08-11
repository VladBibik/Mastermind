package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.localization.messages.common.ConsoleTimeFormattingMessages;
import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.values.Time;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeToStringFormatterTest {
    private static TimeFormattingMessages messages;
    private static Time time;

    @BeforeAll
    static void init() {
        messages = new ConsoleTimeFormattingMessages(
                ResourceBundle.getBundle("i18n.common.time_formatting"));
        time = new Time(12, 34, 25, 377);
    }

    @Test
    @DisplayName("Returns correctly formatted time")
    void testTimeFormattingAccuracy() {
        String expectedTime = "12h 34min 25sec 377ms";

        assertEquals(expectedTime, TimeToStringFormatter.format(time, messages));
    }
}
