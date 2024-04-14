package aprendizajeAutomatico;

import excepciones.KmeansExceptionGruposMayorDatos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import table.*;

import java.util.List;

class KmeansTest {

    @Test
    void estimate() throws KmeansExceptionGruposMayorDatos {

        Kmeans kmeans = new Kmeans(3, 20, 190);

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

        assertEquals(3, kmeans.estimate(ej7));
        assertEquals(1, kmeans.estimate(ej1));
        assertEquals(2, kmeans.estimate(ej5));
        assertEquals(2, kmeans.estimate(ej9));



    }
}