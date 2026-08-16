package dev.bibikvlad.mastermind.values;

public class TimeValidator {
    private TimeValidator() {
        throw new AssertionError("Cannot instantiate a utility class");
    }

    public static void validate(long hour, long minutes, long second, long millisecond) {
        if (hour < 0) {
            throw new IllegalArgumentException("Hour cannot be negative");
        } else if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        } else if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Seconds must be between 0 and 59");
        } else if (millisecond < 0 || millisecond > 999) {
            throw new IllegalArgumentException("Milliseconds must be between 0 and 999");
        }
    }
}
