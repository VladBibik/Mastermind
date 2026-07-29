package dev.bibikvlad.mastermind.persistence.sqlite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SQLiteTimestampParser {
    private SQLiteTimestampParser() {
        throw new AssertionError("Can't instantiate a utility class");
    }

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime parse(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }
}
