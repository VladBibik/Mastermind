package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.values.Time;

public class TimeToStringFormatter {
    public static String format(Time time, TimeFormattingMessages messages) {
        StringBuilder stringBuilder = new StringBuilder();

        if (time.hour() > 0) stringBuilder.append(messages.getHours(time.hour()));
        if (time.minutes() > 0) stringBuilder.append(messages.getHours(time.minutes()));
        if (time.second() > 0) stringBuilder.append(messages.getHours(time.second()));

        stringBuilder.append(messages.getHours(time.millisecond()));

        return stringBuilder.toString().trim();
    }
}
