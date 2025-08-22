package dev.bibikvlad.utils.formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SQLiteTimestampFormatter {
    //TODO: Need a refactor!!! Just temp class to be able to see normal result!
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.US);

    public static LocalDateTime parse(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }

    public static String format(LocalDateTime date) {
        return FORMATTER.format(date);
    }
}
