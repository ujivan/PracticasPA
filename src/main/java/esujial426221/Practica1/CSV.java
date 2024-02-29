package esujial426221.Practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSV extends Table{
    public static Table readTable(String file)  {
        Table table = new Table();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    table.headers.add(String.valueOf(Arrays.asList(line.split(","))));
                    firstLine = false;
                } else {
                    String[] output = line.split(",");
                    List<Double> outputDouble = new ArrayList<>();
                    for (String value : output) {
                        outputDouble.add(Double.valueOf(value));
                    }
                    table.addRow(outputDouble);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return table;
    }

    public static TableWithLabels readTableWithLabel(String file) {
        TableWithLabels table = new TableWithLabels();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    table.headers.add(String.valueOf(Arrays.asList(line.split(","))));
                    firstLine = false;
                } else {
                    List<String> output = List.of(line.split(","));
                    List<Double> outputDouble = new ArrayList<>();
                    for (String value : output) {
                        outputDouble.add(Double.valueOf(value));
                    }
                    int labelRef = table.search(output.get(output.size()-1));

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return table;
    }
}
