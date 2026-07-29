package dev.bibikvlad.utils.formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SQLiteTimestampFormatter {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.US);

    public static LocalDateTime parse(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }
}
