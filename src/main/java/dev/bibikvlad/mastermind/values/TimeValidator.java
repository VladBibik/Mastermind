package dev.bibikvlad.mastermind.values;

public class TimeValidator {
    private TimeValidator() {
        throw new AssertionError("Cannot instantiate a utility class");
    }

    public static void validate(long hour, long minutes, long second, long millisecond) {
        if (hour < 0) {
            throw new IllegalArgumentException("Invalid hour value");
        } else if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Invalid minutes value");
        } else if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Invalid seconds value");
        } else if (millisecond < 0 || millisecond > 999) {
            throw new IllegalArgumentException("Invalid milliseconds value");
        }
    }
}
