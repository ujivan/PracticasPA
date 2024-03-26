package AprendizajeAutomatico;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import Table.*;
import Aritmetica.CalculoDistancias;

import java.util.List;

class KmeansTest {

    int grupos = 3;
    int repes = 4;
    long seed = 100;

    Kmeans kmeans = new Kmeans(grupos, repes, seed);

    @Test
    void train() {

    }

    @Test
    void estimate() {

        Table table = new Table();
        List<Double> ej1 = List.of(2.3, 4.5, 3.8);
        List<Double> ej2 = List.of(6.8, 4.2, 1.7);
        table.addRow(ej1);
        table.addRow(ej2);

        assertEquals(2, kmeans.estimate(ej1));

    }
}