package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.model.Time;

public class MillisecondsToTimeFormatter {
    public static Time format(long milliseconds) {
        long seconds = milliseconds / 1000;

        if (seconds < 1) {
            return new Time(0, 0, 0, milliseconds);
        }

        long millisecondsLeftovers = milliseconds % 1000;

        if (seconds > 60) {
            long minutes = seconds / 60;
            long secondsLeftovers = seconds % 60;

            if (minutes > 60) {
                long hours = minutes / 60;
                long minutesLeftovers = minutes % 60;

                return new Time(hours, minutesLeftovers, secondsLeftovers, millisecondsLeftovers);
            }

            return new Time(0, minutes, secondsLeftovers, millisecondsLeftovers);
        }

        return new Time(0, 0, seconds, millisecondsLeftovers);
    }
}
