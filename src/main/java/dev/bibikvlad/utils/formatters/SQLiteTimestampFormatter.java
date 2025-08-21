package dev.bibikvlad.utils.formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SQLiteTimestampFormatter {
    public static LocalDateTime format(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
}
