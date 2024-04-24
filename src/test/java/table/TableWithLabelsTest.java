package table;

import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {

    @Test
    void numFilaTableWithLabels() {

        TableWithLabels tabla = new TableWithLabels();
        List<Double> ej1 = List.of(1.8, 1.8, 1.8);
        List<Double> ej2 = List.of(2.7, 2.7, 2.7);
        List<Double> ej3 = List.of(1.8, 1.8, 1.8);
        List<Double> ej4 = List.of(2.7, 2.7, 2.7);
        List<Double> ej5 = List.of(1.8, 1.8, 1.8);
        List<Double> ej6 = List.of(2.7, 2.7, 2.7);

        tabla.addRow(new RowWithLabel(ej1, 1));
        tabla.addRow(new RowWithLabel(ej2, 2));
        tabla.addRow(new RowWithLabel(ej3, 3));
        tabla.addRow(new RowWithLabel(ej4, 4));
        tabla.addRow(new RowWithLabel(ej5, 5));
        tabla.addRow(new RowWithLabel(ej6, 6));

        assertEquals(tabla.size(tabla), 6);

    }

    @Test
    void columnasTableWithLabels()  {

        List<String> cabeceras = new ArrayList<>();
        cabeceras.add("Zorros");
        cabeceras.add("Leones");
        cabeceras.add("Elefentes");
        cabeceras.add("Perros");
        cabeceras.add("Gatos");

        TableWithLabels table = new TableWithLabels();
        table.addHeaders(cabeceras);

        assertEquals(table.sizeHeaders(table), 5);

    }

    @Test
    void headersTableWithLabels() {

        List<String> cabeceras = new ArrayList<>();
        cabeceras.add("Zorros");
        cabeceras.add("Leones");
        cabeceras.add("Elefentes");
        cabeceras.add("Perros");
        cabeceras.add("Gatos");

        TableWithLabels table = new TableWithLabels();
        table.addHeaders(cabeceras);

        assertEquals(table.getHeaders(), cabeceras);

    }
    @Test
    void numFilasAsignadaTableWithLabels() throws FileNotFoundException {

        TableWithLabels tabla = new TableWithLabels();
        List<Double> ej1 = List.of(1.8, 1.8, 1.8);
        List<Double> ej2 = List.of(2.7, 2.7, 2.7);
        List<Double> ej3 = List.of(1.8, 1.8, 1.8);
        List<Double> ej4 = List.of(2.7, 2.7, 2.7);
        List<Double> ej5 = List.of(1.8, 1.8, 1.8);
        List<Double> ej6 = List.of(2.7, 2.7, 2.7);

        tabla.addRow(new RowWithLabel(ej1, 0));
        tabla.addRow(new RowWithLabel(ej2, 1));
        tabla.addRow(new RowWithLabel(ej3, 2));
        tabla.addRow(new RowWithLabel(ej4, 3));
        tabla.addRow(new RowWithLabel(ej5, 4));
        tabla.addRow(new RowWithLabel(ej6, 5));

        assertEquals(tabla.search("1"), 0);
        assertEquals(tabla.search("2"), 1);
        assertEquals(tabla.search("3"), 2);


    }

    @Test
    void recuperarContenidoFilasTableWithLabels()  {

        TableWithLabels tabla = new TableWithLabels();
        List<Double> ej1 = List.of(1.8, 1.8, 1.8);
        RowWithLabel row1 = new RowWithLabel(ej1, 1);
        List<Double> ej2 = List.of(2.7, 2.7, 2.7);
        RowWithLabel row2 = new RowWithLabel(ej2, 2);

        tabla.addRow(row1);
        tabla.addRow(row2);

        assertEquals(tabla.getRowAt(1), row2);


    }





}