package dev.bibikvlad.utils.formatters;

public class ClockDisplayFormatter {
    private static final long MAX_DISPLAY_MILLIS = (99 * 60 * 1000) + (99 * 1000) + 999;
    private static final String MAX_TIME = "99:99:999";

    private ClockDisplayFormatter() {
        throw new AssertionError("Cannot instantiate ClockDisplayFormatter");
    }

    public static String format(long milliseconds) {
        if (milliseconds >= MAX_DISPLAY_MILLIS) {
            return MAX_TIME;
        }

        long minutes = milliseconds / 60_000;
        long millisRemainingAfterMinutes = milliseconds % 60_000;

        long seconds = millisRemainingAfterMinutes / 1000;
        long millis = millisRemainingAfterMinutes % 1000;


        return String.format("%02d:%02d:%03d", minutes, seconds, millis);
    }
}
