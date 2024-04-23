package aprendizajeAutomatico;

import aritmetica.EuclideanDistance;
import aritmetica.ManhattanDistance;
import excepciones.KmeansExceptionGruposMayorDatos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import table.*;

import java.util.List;

class KmeansTest {

    @Test
    void estimateEuclidean() throws KmeansExceptionGruposMayorDatos {

        Kmeans kmeans = new Kmeans(3, 20, 190, new EuclideanDistance());

        Table table = new Table();
        List<Double> ej1 = List.of(1.0, 1.0, 1.0);
        List<Double> ej2 = List.of(0.7, 0.7, 0.7);
        List<Double> ej3 = List.of(0.8, 0.8, 0.8);
        List<Double> ej4 = List.of(0.9, 0.9, 0.9);
        List<Double> ej5 = List.of(68.0, 68.0, 68.0);
        List<Double> ej6 = List.of(155.5, 155.5, 155.5);
        List<Double> ej7 = List.of(156.0, 156.0, 156.0);
        List<Double> ej8 = List.of(154.0, 154.0, 154.0);
        List<Double> ej9 = List.of(78.5,78.5,78.5);
        List<Double> ej10 = List.of(60.5,60.5);
        List<Double> ej11 = List.of(120.0, 120.0, 120.0, 120.0);

        table.addRow(ej1);
        table.addRow(ej2);
        table.addRow(ej3);
        table.addRow(ej4);
        table.addRow(ej5);
        table.addRow(ej6);
        table.addRow(ej7);
        table.addRow(ej8);
        table.addRow(ej9);

        kmeans.train(table);

        Row row1 = table.getRowAt(1);

        assertEquals(3, kmeans.estimate(row1));
        /*
        assertEquals(1, kmeans.estimate(ej1));
        assertEquals(2, kmeans.estimate(ej5));
        assertEquals(2, kmeans.estimate(ej9));
        assertThrows(IllegalArgumentException.class, ()-> kmeans.estimate(ej10));

         */
    }
    @Test
    void estimateManhattan() throws KmeansExceptionGruposMayorDatos {

        Kmeans kmeans = new Kmeans(2, 20, 44, new ManhattanDistance());

        Table table = new Table();

        List<Double> ej1 = List.of(1.8, 1.8, 1.8);
        List<Double> ej2 = List.of(2.7, 2.7, 2.7);
        List<Double> ej3 = List.of(2.8, 2.8, 2.8);
        List<Double> ej4 = List.of(1.9, 1.9, 1.9);
        List<Double> ej5 = List.of(132.0, 132.0, 132.0);
        List<Double> ej6 = List.of(132.5, 132.5, 132.5);
        List<Double> ej7 = List.of(142.5, 142.5, 142.5);
        List<Double> ej8 = List.of(140.0, 140.0, 140.0);
        List<Double> ej9 = List.of(140.0, 140.0);

        table.addRow(ej1);
        table.addRow(ej2);
        table.addRow(ej3);
        table.addRow(ej4);
        table.addRow(ej5);
        table.addRow(ej6);
        table.addRow(ej7);
        table.addRow(ej8);

        kmeans.train(table);
        Row row1 = table.getRowAt(1);

        assertEquals(3, kmeans.estimate(row1));
        /*

        assertEquals(1, kmeans.estimate(ej1));
        assertEquals(2, kmeans.estimate(ej5));
        assertEquals(2, kmeans.estimate(List.of(180.0, 180.0, 180.0)));
        assertThrows(IllegalArgumentException.class, ()-> kmeans.estimate(ej9));

         */


    }
}