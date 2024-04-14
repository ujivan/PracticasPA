package aprendizajeAutomatico;

import table.CSV;
import table.TableWithLabels;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;
import aritmetica.CalculoDistancias;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {

    @Test
    void pruebaMetricaEuclidiana() {
        List<Double> ej1 = List.of(2.3, 4.5, 3.8);
        List<Double> ej2 = List.of(6.8, 4.2, 1.7);
        List<Double> ej3 = List.of(6.8, 4.2);
        List<Double> ej4 = List.of(0.5, 7.8, 12.6, 2.6);
        List<Double> ej5 = List.of(3.2, 4.9, 2.6, 1.8);
        List<Double> ej6 = List.of(5.2);
        List<Double> ej7 = List.of();
        List<Double> ej8 = List.of(5.2, 7.4);
        List<Double> ej9 = List.of();
        assertEquals(4.974, CalculoDistancias.metricaEuclidiana(ej1, ej2), 0.001);
        assertEquals(3.577, CalculoDistancias.metricaEuclidiana(ej3, ej8), 0.001);
        assertEquals(10.786, CalculoDistancias.metricaEuclidiana(ej4, ej5), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> CalculoDistancias.metricaEuclidiana(ej3, ej4));
        assertThrows(IllegalArgumentException.class, ()-> CalculoDistancias.metricaEuclidiana(ej1, ej7));
        assertThrows(IllegalArgumentException.class, ()-> CalculoDistancias.metricaEuclidiana(ej2, ej6));
        assertThrows(IllegalArgumentException.class, ()-> CalculoDistancias.metricaEuclidiana(ej8, ej2));
        assertThrows(IllegalArgumentException.class, ()-> CalculoDistancias.metricaEuclidiana(ej7, ej6));
        assertThrows(IllegalArgumentException.class, ()-> CalculoDistancias.metricaEuclidiana(ej7, ej9));

    }

    @Test
    void pruebaEstimate() throws FileNotFoundException {
        KNN knn = new KNN();

        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        TableWithLabels tabla = CSV.readTableWithLabel(rutaFichero);

        knn.train(tabla);

        List<Double> ejemplo = List.of(4.4,3.2,1.3,0.2);
        List<Double> ejemplo2 = List.of(5.7,2.8,4.5,1.3);
        List<Double> ejemplo3 = List.of(6.5,3.2,5.1,2.0);
        List<Double> ejemplo4 = List.of();
        List<Double> ejemplo5 = List.of(6.5,3.2,5.1);
        List<Double> ejemplo6 = List.of(1.2);
        List<Double> ejemplo7 = List.of(4.4,0.4,5.9,3.7,6.8);
        List<Double> ejemplo8 = List.of(3.4,7.4,0.5,1.3);
        List<Double> ejemplo9 = List.of(4.7,2.1,3.2,1.5);
        List<Double> ejemplo10 = List.of(5.1,3.5,1.1,6.8);

        assertEquals(1, knn.estimate(ejemplo));
        assertEquals(2, knn.estimate(ejemplo2));
        assertEquals(3, knn.estimate(ejemplo3));
        assertEquals(1, knn.estimate(ejemplo8));
        assertEquals(2, knn.estimate(ejemplo9));
        assertEquals(3, knn.estimate(ejemplo10));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo4));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo5));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo6));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo7));

    }
}