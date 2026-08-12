package dev.bibikvlad.mastermind.values;

public class TimeValidator {
    public boolean validate(long hour, long minutes, long second, long millisecond) {
        if (hour < 0) {
            return false;
        } else if (minutes < 0 || minutes > 59) {
            return false;
        } else if (second < 0 || second > 59) {
            return false;
        } else return millisecond >= 0 && millisecond <= 999;
    }
}
