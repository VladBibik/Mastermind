package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.values.Time;

public class TimeToStringFormatter {
    public static String format(Time time, TimeFormattingMessages messages) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean hasHours = time.hour() > 0;
        boolean hasMinutes = hasHours || time.minutes() > 0;
        boolean hasSeconds = hasMinutes || time.second() > 0;

        if (hasHours) stringBuilder.append(messages.getHours(time.hour())).append(" ");
        if (hasMinutes) stringBuilder.append(messages.getMinutes(time.minutes())).append(" ");
        if (hasSeconds) stringBuilder.append(messages.getSeconds(time.second())).append(" ");

        stringBuilder.append(messages.getMilliseconds(time.millisecond()));

        return stringBuilder.toString();
    }
}
