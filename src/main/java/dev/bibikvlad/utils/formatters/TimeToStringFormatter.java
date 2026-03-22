package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.values.Time;

public class TimeToStringFormatter {
    public static String format(Time time, TimeFormattingMessages messages) {
        StringBuilder stringBuilder = new StringBuilder();

        if (time.hour() > 0) stringBuilder.append(messages.getHours(time.hour())).append(" ");
        if (time.minutes() > 0) stringBuilder.append(messages.getMinutes(time.minutes())).append(" ");
        if (time.second() > 0) stringBuilder.append(messages.getSeconds(time.second())).append(" ");

        stringBuilder.append(messages.getMillisecond(time.millisecond()));

        return stringBuilder.toString().trim();
    }
}
