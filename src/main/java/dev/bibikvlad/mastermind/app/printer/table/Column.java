package dev.bibikvlad.mastermind.app.printer.table;

import java.util.function.Function;

public class Column<T> {
    private final String header;
    private final Function<T, String> valueExtractor;

    public Column(String header, Function<T, String> valueExtractor) {
        this.header = header;
        this.valueExtractor = valueExtractor;
    }

    public String getHeader() {
        return header;
    }

    public Function<T, String> getValueExtractor() {
        return valueExtractor;
    }
}
