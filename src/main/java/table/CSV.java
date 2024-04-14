package table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSV extends Table{
    public static Table readTable(String file)  {
        Table table = new Table();
        // Leemos las lineas del archivo mediante un Bufferreader
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    // Añadimos las cabeceras de la primera linea a la tabla indicando que se separan con una coma
                    table.addHeaders(Arrays.asList(line.split(",")));
                    firstLine = false;
                } else {
                    // Creamos vector de String para que almacene los valores de cada fila y despues pueda recorrerlos y almacenarlos en una lista, para acabar añadiendolos a la tabla
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
                    table.addHeaders(Arrays.asList(line.split(",")));
                    firstLine = false;
                } else {
                    // En este caso, los valores se almacenan directamente en una lista de Strings para después poder recorrerla con un bucle for y almacenar los valores en una lista de Double, que es tipo real de estos
                    List<String> output = List.of(line.split(","));
                    List<Double> outputDouble = new ArrayList<>();
                    for (int i = 0; i < output.size()-1; i++) {
                        outputDouble.add(Double.valueOf(output.get(i)));
                    }
                    // Buscamos el numero correspondiente a la etiqueta mediante el metodo search y así poder añadir los valores con la etiqueta a la tabla
                    int labelRef = table.search(output.get(output.size()-1));
                    table.addRowWithLabel(outputDouble, labelRef);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return table;
    }
}
