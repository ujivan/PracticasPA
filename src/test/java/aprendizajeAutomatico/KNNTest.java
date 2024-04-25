package aprendizajeAutomatico;
import readFiles.*;
import aritmetica.ManhattanDistance;
import table.TableWithLabels;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;
import aritmetica.EuclideanDistance;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {

    @Test
    void pruebaEstimateEuclidean() throws FileNotFoundException {
        KNN knn = new KNN(new EuclideanDistance());


        String rutaFichero = "archivos" + File.separator+"iris.csv";
        CSVLabeledFileReader labeledReader = new CSVLabeledFileReader(rutaFichero);
        TableWithLabels tabla = (TableWithLabels) labeledReader.readTableFromSource();

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
        List<Double> ejemplo11 = List.of(5.1,7.8,2.5,6.8);

        assertEquals(1, knn.estimate(ejemplo));
        assertEquals(2, knn.estimate(ejemplo2));
        assertEquals(3, knn.estimate(ejemplo3));
        assertEquals(1, knn.estimate(ejemplo8));
        assertEquals(2, knn.estimate(ejemplo9));
        assertEquals(2, knn.estimate(ejemplo10));
        assertEquals(3, knn.estimate(ejemplo11));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo4));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo5));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo6));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo7));

    }

    @Test
    void pruebaEstimateManhattan(){
        KNN knn = new KNN(new ManhattanDistance());

        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        CSVLabeledFileReader labeledReader = new CSVLabeledFileReader(rutaFichero);
        TableWithLabels tabla = (TableWithLabels) labeledReader.readTableFromSource();

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
        assertEquals(1, knn.estimate(ejemplo10));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo4));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo5));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo6));
        assertThrows(IllegalArgumentException.class, ()-> knn.estimate(ejemplo7));
    }
}