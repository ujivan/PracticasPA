package AprendizajeAutomatico;
import Table.*;
import Aritmetica.CalculoDistancias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Kmeans {

    int numClusters;
    int numIterations;
    long seed;

    HashMap<Row, Integer> asignacionClusters = new HashMap<>();



    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
    }
    public void train(Table datos) {
        List<List<Double>> clusters = new ArrayList<>(numClusters);

        for (int i = 0; i < numClusters; i++) {
            Random random = new Random(seed);
            Row columnaAleatoia = datos.getRowAt(random.nextInt(datos.rows.size()));
            clusters.add(columnaAleatoia.getData());
        }
        for (int i = 0; i < numIterations; i++){
            double minDistancia = Double.MAX_VALUE;
            for (Row row : datos.rows){
                int numRespresentante = 0;
                for (int j = 0; j < numClusters; j++){
                    double distancia = CalculoDistancias.metricaEuclidiana(row.getData(), clusters.get(j));
                    if (distancia < minDistancia) {
                        minDistancia = distancia;
                        numRespresentante = j;
                    }
                }
                asignacionClusters.put(row, numRespresentante);




            }

        }

    }


}
