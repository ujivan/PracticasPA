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
                    // Leer datos
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
}
