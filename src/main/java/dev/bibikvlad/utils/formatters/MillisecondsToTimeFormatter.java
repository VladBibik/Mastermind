package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.model.Time;

import java.time.Duration;

public class MillisecondsToTimeFormatter {
    public static Time format(long milliseconds) {
        Duration duration = Duration.ofMillis(milliseconds);

        return new Time(
                duration.toHours(),
                duration.toMinutes(),
                duration.toSeconds(),
                duration.toMillis()
        );
    }
}
