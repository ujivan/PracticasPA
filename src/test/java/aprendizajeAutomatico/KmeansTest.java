package aprendizajeAutomatico;

import aritmetica.EuclideanDistance;
import aritmetica.ManhattanDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import table.*;

import java.util.List;

class KmeansTest {

    @Test
    void estimateEuclidean() throws KmeansExceptionGruposMayorDatos {

        Kmeans kmeans = new Kmeans(2, 20, 190, new EuclideanDistance());

        Table table = new Table();
        List<Double> ej1 = List.of(1.0, 1.0, 1.0);
        List<Double> ej2 = List.of(0.7, 0.7, 0.7);
        List<Double> ej3 = List.of(0.8, 0.8, 0.8);
        List<Double> ej4 = List.of(0.9, 0.9, 0.9);
        List<Double> ej5 = List.of(168.0, 168.0, 168.0);
        List<Double> ej6 = List.of(155.5, 155.5, 155.5);
        List<Double> ej7 = List.of(156.0, 156.0, 156.0);
        List<Double> ej8 = List.of(154.0, 154.0, 154.0);
        List<Double> ej9 = List.of(178.5, 178.5, 178.5);
        List<Double> ej10 = List.of(60.5,60.5);
        List<Double> ej11 = List.of(168.5, 178.5, 168.5);

        table.addRow(new Row(ej1));
        table.addRow(new Row(ej2));
        table.addRow(new Row(ej3));
        table.addRow(new Row(ej4));
        table.addRow(new Row(ej5));
        table.addRow(new Row(ej6));
        table.addRow(new Row(ej7));
        table.addRow(new Row(ej8));
        table.addRow(new Row(ej9));

        kmeans.train(table);

        Row row1 = table.getRowAt(0);
        Row row2 = table.getRowAt(4);
        Row row3 = new Row(ej10);
        Row row4 = new Row(ej11);

        assertEquals(1, kmeans.estimate(row1));
        assertEquals(2, kmeans.estimate(row2));
        assertEquals(2, kmeans.estimate(row4));
        assertThrows(IllegalArgumentException.class, ()-> kmeans.estimate(row3));

    }
    @Test
    void estimateManhattan() throws KmeansExceptionGruposMayorDatos {

        Kmeans kmeans = new Kmeans(2, 20, 44, new ManhattanDistance());

        Table table = new Table();
        List<Double> ej1 = List.of(1.0, 1.0, 1.0);
        List<Double> ej2 = List.of(0.7, 0.7, 0.7);
        List<Double> ej3 = List.of(0.8, 0.8, 0.8);
        List<Double> ej4 = List.of(0.9, 0.9, 0.9);
        List<Double> ej5 = List.of(168.0, 168.0, 168.0);
        List<Double> ej6 = List.of(155.5, 155.5, 155.5);
        List<Double> ej7 = List.of(156.0, 156.0, 156.0);
        List<Double> ej8 = List.of(154.0, 154.0, 154.0);
        List<Double> ej9 = List.of(178.5, 178.5, 178.5);
        List<Double> ej10 = List.of(60.5,60.5);
        List<Double> ej11 = List.of(168.5, 178.5, 168.5);

        table.addRow(new Row(ej1));
        table.addRow(new Row(ej2));
        table.addRow(new Row(ej3));
        table.addRow(new Row(ej4));
        table.addRow(new Row(ej5));
        table.addRow(new Row(ej6));
        table.addRow(new Row(ej7));
        table.addRow(new Row(ej8));
        table.addRow(new Row(ej9));

        kmeans.train(table);

        Row row1 = table.getRowAt(0);
        Row row2 = table.getRowAt(4);
        Row row3 = new Row(ej10);
        Row row4 = new Row(ej11);

        assertEquals(1, kmeans.estimate(row1));
        assertEquals(2, kmeans.estimate(row2));
        assertEquals(2, kmeans.estimate(row4));
        assertThrows(IllegalArgumentException.class, ()-> kmeans.estimate(row3));


    }
}