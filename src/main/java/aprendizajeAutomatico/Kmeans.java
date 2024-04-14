package aprendizajeAutomatico;
import aritmetica.Algorithm;
import excepciones.KmeansExceptionGruposMayorDatos;
import table.*;
import aritmetica.CalculoDistancias;

import java.util.*;

public class Kmeans implements Algorithm<Table, Integer, List<Double>> {

    private int numClusters; // Número de grupos
    private int numIterations; // Número de iteraciones
    private long seed; // Semilla para generación de números aleatorios
    private List<List<Double>> clusters; // Lista de centroides

    HashMap<Row, Integer> asignacionClusters = new HashMap<>(); // Mapa con filas con su cluster asigando

    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.clusters = new ArrayList<>();
    }
    @Override
    public void train(Table datos) throws KmeansExceptionGruposMayorDatos {

        if (numClusters > datos.rows.size()) {
            throw new KmeansExceptionGruposMayorDatos(" El número de grupos es mayor que el número de datos");
        }
        añadirClustersAleatorios(datos);

        for (int i = 0; i < numIterations; i++){
            asignacionClusters.clear();
            for (Row row : datos.rows){
                int numRepresentante = buscarClusterMasCercano(row);
                asignacionClusters.put(row, numRepresentante);

            }
            for (int cluster = 0; cluster < numClusters; cluster++) {
                List<Row> puntosAsignados = buscarGruposPuntos(cluster +1);
                List<Double> nuevoCentroide = calcularCentroide(puntosAsignados);
                clusters.set(cluster, nuevoCentroide);
            }
        }
    }

    private int buscarClusterMasCercano(Row row){
        double minDistancia = Double.MAX_VALUE;
        int numRepresentante = 0;
        for (int j = 0; j < numClusters; j++){
            double distancia = CalculoDistancias.metricaEuclidiana(row.getData(), clusters.get(j));
            if (distancia < minDistancia) {
                minDistancia = distancia;
                numRepresentante = j + 1;
            }
        }
        return numRepresentante;

    }
    private void añadirClustersAleatorios (Table datos){
        Random random = new Random(seed);

        HashSet<Integer> selectedIndices = new HashSet<>();

        for (int i = 0; i < numClusters; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(datos.rows.size());
            } while (selectedIndices.contains(randomIndex));

            selectedIndices.add(randomIndex);
            Row filaAleatoria = datos.getRowAt(randomIndex);
            clusters.add(filaAleatoria.getData());
        }
    }

    private List<Row> buscarGruposPuntos(Integer cluster){
        List<Row> grupo = new ArrayList<>();
        for (Map.Entry<Row, Integer> entry : asignacionClusters.entrySet()) {

            if (entry.getValue() == cluster){

                grupo.add(entry.getKey());
            }
        }
        return grupo;
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
