package dev.bibikvlad.mastermind.values;

public record Time(long hour, long minutes, long second, long millisecond) {
    public Time {
        TimeValidator validator = new TimeValidator();

        if (!validator.validate(hour, minutes, second, millisecond)) {
            throw new IllegalArgumentException("Invalid hour or minutes or seconds or milliseconds");
        }
    }
}
