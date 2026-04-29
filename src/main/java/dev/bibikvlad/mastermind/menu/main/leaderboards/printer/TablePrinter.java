package dev.bibikvlad.mastermind.menu.main.leaderboards.printer;

import java.util.ArrayList;
import java.util.List;

public class TablePrinter<T> {
    private final int PADDING = 10;

    public void print(List<T> data, List<Column<T>> columns) {
        List<Integer> columnWidths = new ArrayList<>();

        for (int i = 0; i < columns.size(); i++) {
            int temp = i;

            int columnHeaderMaxLength = columns.get(i).getHeader().length();
            int columnValueMaxLength = data
                    .stream()
                    .map(entry -> columns.get(temp).getValueExtractor().apply(entry))
                    .map(String::length)
                    .max(Integer::compareTo)
                    .orElse(0);

            columnWidths.add(Math.max(columnHeaderMaxLength, columnValueMaxLength) + PADDING);
        }
    }
}
