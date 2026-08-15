package dev.bibikvlad.mastermind.values;

public record Time(long hour, long minutes, long second, long millisecond) {
    public Time {
        TimeValidator.validate(hour, minutes, second, millisecond);
    }
}
