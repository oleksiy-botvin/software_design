package util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TableUtil {

    public static void printTable(@NotNull Column... columns) {
        printTable("", columns);
    }

    public static void printTable(@NotNull String title, @NotNull Column... columns) {
        if (columns.length == 0) return;

        int rowsSize = 0;
        int tabWidth = 0;
        for (Column column : columns) {
            tabWidth += column.getWidth() + 3;
            rowsSize = Math.max(rowsSize, column.rows.size());
        }

        if (rowsSize == 0) return;

        System.out.println();

        if (!title.isBlank()) {
            String padding = " ".repeat((tabWidth - title.length()) / 2);
            System.out.println(padding + title + padding);
        }

        for (int index = 0; index < rowsSize; index++) {
            int printType = 0;
            while (printType >= 0 && printType <= 3) {
                boolean isEmpty = true;

                for (int i = 0; i < columns.length; i++) {
                    Column column = columns[i];
                    Row row = column.getRow(index);

                    if (printType == 0) {
                        System.out.print(
                                index == 0 ? (i == 0 ? '┌' : '┬') : (i == 0 ? '├' : '┼')

                        );
                        System.out.print("─".repeat(row.getWidth() + 2));
                        if (i == columns.length - 1) System.out.println(
                                index == 0 ? '┐' : '┤'
                        );

                    } else if (printType == 1) {

                        System.out.print('│');
                        System.out.print(" " + row.nextLine() + " ");
                        if (i == columns.length - 1) System.out.println('│');
                        if (row.hasNextLine()) isEmpty = false;

                    } else {
                        System.out.print(i == 0 ? '└' : '┴');
                        System.out.print("─".repeat(row.getWidth() + 2));
                        if (i == columns.length - 1) System.out.println('┘');

                    }
                }

                if (printType == 0) printType = 1;
                else if (printType == 1 && isEmpty) printType = index == rowsSize -1 ? 2 : -1;
                else if (printType == 2) printType = -1;
            }
        }

    }

    public static class Column {

        private final int width;
        private final @NotNull List<Row> rows;

        public Column(int width, @NotNull String... valueRows) {
            this.width = Math.max(width, 1);
            this.rows = Arrays.stream(valueRows).map(it -> new Row(width, it)).toList();
        }

        public int getWidth() {
            return width;
        }

        public @NotNull Row getRow(int index) {
            return index < rows.size() ? rows.get(index) : new Row(width, "");
        }

        public static class Builder {

            private int width;
            @NotNull private final List<String> rows = new ArrayList<>();

            public Builder width(int width) {
                this.width = width;
                return this;
            }

            public Builder addValueRows(String... valueRows) {
                this.rows.addAll(Arrays.asList(valueRows));
                return this;
            }

            public Builder addValueRow(String valueRow) {
                this.rows.add(valueRow);
                return this;
            }

            public Column build() {
                return new Column(width, rows.toArray(new String[0]));
            }
        }
    }

    public static class Row {

        private final int width;
        private final @NotNull String value;
        private final @NotNull Iterator<String> lines;

        private Row(int width, @NotNull String value) {
            this.width = width;
            this.value = value;
            List<String> items = new ArrayList<>();
            int size = value.length() / width + (value.length() % width == 0 ? 0 : 1);
            for (int i = 0; i < size; i++) {
                int endIndex = Math.min(i * width + width, value.length());
                items.add(value.substring(i == 0 ? 0 : i * width, endIndex).trim());
            }
            lines = items.iterator();
        }

        public @NotNull String getValue() {
            return value;
        }

        public int getWidth() {
            return width;
        }

        public boolean hasNextLine() {
            return lines.hasNext();
        }

        public @NotNull String nextLine() {
            StringBuilder line = new StringBuilder(lines.hasNext() ? lines.next() : "");
            line.append(" ".repeat(Math.max(0, width - line.length())));
            return line.toString();
        }
    }
}
