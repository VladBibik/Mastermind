package dev.bibikvlad.mastermind.menu.main.leaderboards.printer;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;

import java.util.ArrayList;
import java.util.List;

public class TablePrinter<T> {
    private final int PADDING = 10;

    private final Printer printer;

    public TablePrinter(AppContext appContext) {
        this.printer = appContext.printer();
    }

    public void print(List<T> data, List<Column<T>> columns) {
        //1.Width Calculation
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

        //2. Print Header
        StringBuilder formattingBuilder = new StringBuilder();

        for (int i = 0; i < columnWidths.size(); i++) {
            formattingBuilder.append("%-" + columnWidths.get(i) + "s");
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
