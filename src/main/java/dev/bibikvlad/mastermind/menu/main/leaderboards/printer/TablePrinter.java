package dev.bibikvlad.mastermind.menu.main.leaderboards.printer;

import dev.bibikvlad.mastermind.app.printer.Printer;

import java.util.List;

public class TablePrinter<T> {
    private static final int PADDING = 10;

    private final Printer printer;

    public TablePrinter(Printer printer) {
        this.printer = printer;
    }

    public void print(List<T> data, List<Column<T>> columns) {
        //1.Width Calculation
        List<Integer> columnWidths = columns
                .stream()
                .mapToInt(column -> {
                    int columnHeaderLength = column.getHeader().length();
                    int columnValueMaxLength = data
                            .stream()
                            .map(entry -> column.getValueExtractor().apply(entry))
                            .map(String::length)
                            .max(Integer::compareTo)
                            .orElse(0);

                    return Math.max(columnHeaderLength, columnValueMaxLength) + PADDING;
                })
                .boxed()
                .toList();

        //2. Print Header
        StringBuilder formattingBuilder = new StringBuilder();

        for (Integer columnWidth : columnWidths) {
            formattingBuilder.append("%-").append(columnWidth).append("s");
        }

        String formatting = formattingBuilder.toString();
        String header = String.format(formatting, columns
                .stream()
                .map(Column::getHeader)
                .toArray());

        printer.printMessage(header);

        //3. Print Divider
        int dividerLineLength = columnWidths.stream().reduce(0, Integer::sum);

        printer.printMessage("-".repeat(dividerLineLength));

        //4. Print Rows
        data.forEach(entry -> {
            String row = String.format(formatting, columns
                    .stream()
                    .map(column -> column.getValueExtractor().apply(entry))
                    .toArray());

            printer.printMessage(row);
        });
    }
}
