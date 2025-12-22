package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.values.Time;

public class TimeToStringFormatter {
    public static String format(Time time) {
        StringBuilder stringBuilder = new StringBuilder();

        if (time.hour() > 0) stringBuilder.append(time.hour()).append("h ");
        if (time.minutes() > 0) stringBuilder.append(time.minutes()).append("min ");
        if (time.second() > 0) stringBuilder.append(time.second()).append("sec ");

        stringBuilder.append(time.millisecond()).append("ms");

        return stringBuilder.toString().trim();
    }
}

class Test {
    public static void main(String[] args) {
        Time time = MillisecondsToTimeFormatter.format(890645645);

        System.out.println(TimeToStringFormatter.format(time));
    }
}