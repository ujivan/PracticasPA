package table;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void numFilasTable() {

        Table tabla = new Table();
        List<Double> ej1 = List.of(1.8, 1.8, 1.8);
        List<Double> ej2 = List.of(2.7, 2.7, 2.7);
        List<Double> ej3 = List.of(1.8, 1.8, 1.8);
        List<Double> ej4 = List.of(2.7, 2.7, 2.7);
        List<Double> ej5 = List.of(1.8, 1.8, 1.8);
        List<Double> ej6 = List.of(2.7, 2.7, 2.7);

        tabla.addRow(new Row(ej1));
        tabla.addRow(new Row(ej2));
        tabla.addRow(new Row(ej3));
        tabla.addRow(new Row(ej4));
        tabla.addRow(new Row(ej5));
        tabla.addRow(new Row(ej6));

        assertEquals(tabla.size(tabla), 6);

    }
    @Test
    void columnasTable() {

        List<String> cabeceras = new ArrayList<>();
        cabeceras.add("Zorros");
        cabeceras.add("Leones");
        cabeceras.add("Elefentes");
        cabeceras.add("Perros");
        cabeceras.add("Gatos");

        Table table = new Table();
        table.addHeaders(cabeceras);

        assertEquals(table.sizeHeaders(table), 5);

    }

    @Test
    void headersTable()  {

        List<String> cabeceras = new ArrayList<>();
        cabeceras.add("Zorros");
        cabeceras.add("Leones");
        cabeceras.add("Elefentes");
        cabeceras.add("Perros");
        cabeceras.add("Gatos");

        Table table = new Table();
        table.addHeaders(cabeceras);

        assertEquals(table.getHeaders(table), cabeceras);

    }

    @Test
    void recuperarContenidoFilasTable() {

        Table tabla = new Table();
        List<Double> ej1 = List.of(1.8, 1.8, 1.8);
        Row row1 = new Row(ej1);
        List<Double> ej2 = List.of(2.7, 2.7, 2.7);
        Row row2 = new Row(ej2);
        List<Double> ej3 = List.of(3.8, 3.8, 3.8);
        Row row3 = new Row(ej3);


        tabla.addRow(row1);
        tabla.addRow(row2);
        tabla.addRow(row3);

        assertEquals(tabla.getRowAt(1), row2);
        assertEquals(tabla.getRowAt(2), row3);

    }



}