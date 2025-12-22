package dev.bibikvlad.utils.formatters;

import dev.bibikvlad.mastermind.values.Time;

import java.time.Duration;

public class MillisecondsToTimeFormatter {
    public static Time format(long milliseconds) {
        Duration duration = Duration.ofMillis(milliseconds);

        return new Time(
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart(),
                duration.toMillisPart()
        );
    }
}
