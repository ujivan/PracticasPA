package esujial426221.Practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    public Table readTable(String file) {
        Table table = new Table();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (firstLine) {
                    for (String value : values) {
                        table.headers.add(value);
                    }
                    firstLine = false;
                } else {
                    Row row = new Row();
                    for (String value : values) {
                        row.getData().add(Double.parseDouble(value));
                    }
                    table.addRow(row);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return table;
    }

    public TableWithLabels readTableWithLabel(String file) {
        TableWithLabels table = new TableWithLabels();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (firstLine) {
                    for (String value : values) {
                        table.headers.add(value);
                    }
                    firstLine = false;
                } else {
                    RowWithLabel row = new RowWithLabel();
                    for (String value : values) {
                        row.getData().add(Double.parseDouble(value));
                    }
                    //table.addRowLabel(row);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return table;
    }
}
