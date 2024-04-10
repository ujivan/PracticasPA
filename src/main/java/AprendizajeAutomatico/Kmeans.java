package AprendizajeAutomatico;
import Aritmetica.Algorithm;
import Excepciones.KmeansExceptionGruposMayorDatos;
import Table.*;
import Aritmetica.CalculoDistancias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Kmeans implements Algorithm<Table, Integer, List<Double>> {

    int numClusters;
    int numIterations;
    long seed;

    HashMap<Integer, List<Row>> asignacionClusters = new HashMap<>();
    List<List<Double>> clusters;



    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.clusters = new ArrayList<>(numClusters);
    }
    @Override
    public void train(Table datos) throws KmeansExceptionGruposMayorDatos {

        if (numClusters > datos.rows.size()) {
            throw new KmeansExceptionGruposMayorDatos(" El número de grupos es mayor que el número de datos");
        }

        Random random = new Random(seed);

        for (int i = 0; i < numClusters; i++) {
            Row filaAleatoia = datos.getRowAt(random.nextInt(datos.rows.size()));
            clusters.add(filaAleatoia.getData());
        }
        for (int i = 0; i < numIterations; i++){
            asignacionClusters.clear();

            for (Row row : datos.rows){
                double minDistancia = Double.MAX_VALUE;
                int numRespresentante = -1;
                for (int j = 0; j < numClusters; j++){
                    double distancia = CalculoDistancias.metricaEuclidiana(row.getData(), clusters.get(j));
                    if (distancia < minDistancia) {
                        minDistancia = distancia;
                        numRespresentante = j + 1;
                    }
                }
                if (!asignacionClusters.containsKey(numRespresentante)) {
                    asignacionClusters.put(numRespresentante, new ArrayList<>());
                }
                asignacionClusters.get(numRespresentante).add(row);
            }
            for (int cluster = 0; cluster < numClusters; cluster++) {
                List<Row> puntosAsignados = asignacionClusters.getOrDefault(cluster, new ArrayList<>());
                if (!puntosAsignados.isEmpty()) {
                    List<Double> nuevoCentroide = calcularCentroide(puntosAsignados);
                    clusters.set(cluster, nuevoCentroide);
                }
            }
        }
    }

    private List<Double> calcularCentroide(List<Row> puntos) {
        int dimension = puntos.get(0).getData().size();
        List<Double> centroide = new ArrayList<>(dimension);

        for (int i = 0; i < dimension; i++) {
            centroide.add(0.0);
        }
        for (Row punto : puntos) {
            List<Double> coordenadas = punto.getData();
            for (int i = 0; i < dimension; i++) {
                centroide.set(i, centroide.get(i) + coordenadas.get(i));
            }
        }
        for (int i = 0; i < dimension; i++) {
            centroide.set(i, centroide.get(i) / puntos.size());
        }

        return centroide;
    }

    @Override
    public Integer estimate(List<Double> dato) {
        double minDistancia = Double.MAX_VALUE;
        int numRespresentante = -1;
        for (int i = 0; i < numClusters; i++) {
            double distancia = CalculoDistancias.metricaEuclidiana(dato, clusters.get(i));
            if (distancia < minDistancia) {
                minDistancia = distancia;
                numRespresentante = i + 1;
            }
        }
        return numRespresentante;
    }

}
