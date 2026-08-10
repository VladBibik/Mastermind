package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.localization.messages.common.ConsoleTimeFormattingMessages;
import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.values.Time;
import org.junit.jupiter.api.BeforeAll;

import java.util.ResourceBundle;

class TimeToStringFormatterTest {
    private static TimeFormattingMessages messages;
    private static Time time;

    @BeforeAll
    static void init() {
        messages = new ConsoleTimeFormattingMessages(
                ResourceBundle.getBundle("i18n.common.time_formatting"));
        time = new Time(12, 34, 25, 377);
    }
}
