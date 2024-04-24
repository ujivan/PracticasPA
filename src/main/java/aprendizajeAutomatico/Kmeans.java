package aprendizajeAutomatico;
import aritmetica.Distance;
import table.*;

import java.util.*;

public class Kmeans implements Algorithm<Table, Integer, Row>, Distance{

    private int numClusters; // Número de grupos
    private int numIterations; // Número de iteraciones
    private long seed; // Semilla para generación de números aleatorios
    private List<List<Double>> clusters; // Lista de centroides

    Distance distance;

    HashMap<Integer, List<Row>> asignacionClusters = new HashMap<>(); // Mapa con filas con su cluster asigando
    // mapa con la estimacion y todas las filas en una lista de rows


    public Kmeans(int numClusters, int numIterations, long seed, Distance distance) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.clusters = new ArrayList<>();
        this.distance = distance;

    }
    public void putRow(int numRepresentante, Row row){
        if (asignacionClusters.containsKey(numRepresentante)){
            List<Row> listaRows = asignacionClusters.get(numRepresentante);
            listaRows.add(row);
            asignacionClusters.put(numRepresentante, listaRows);
        } else {
            List<Row> listaRows = new ArrayList<>();
            listaRows.add(row);
            asignacionClusters.put(numRepresentante, listaRows);
        }

    }

    public double calculateDistance(List<Double> data1, List<Double> data2) {
        return distance.calculateDistance(data1, data2);
    }
    @Override
    public void train(Table datos) throws KmeansExceptionGruposMayorDatos {

        if (numClusters > datos.size(datos)) {
            throw new KmeansExceptionGruposMayorDatos(" El número de grupos es mayor que el número de datos");
        }
        añadirClustersAleatorios(datos);

        for (int i = 0; i < numIterations; i++){
            asignacionClusters.clear();
            for (int j = 0; j < datos.size(datos); j++){
                Row row = datos.getRowAt(j);
                int numRepresentante = estimate(row);
                putRow(numRepresentante, row);

            }
            for (int cluster = 0; cluster < numClusters; cluster++) {
                List<Row> puntosAsignados = buscarGruposPuntos(cluster +1);
                List<Double> nuevoCentroide = calcularCentroide(puntosAsignados);
                clusters.set(cluster, nuevoCentroide);
            }
        }
    }


    private void añadirClustersAleatorios (Table datos){
        Random random = new Random(seed);

        HashSet<Integer> selectedIndices = new HashSet<>();

        for (int i = 0; i < numClusters; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(datos.size(datos));
            } while (selectedIndices.contains(randomIndex));

            selectedIndices.add(randomIndex);
            Row filaAleatoria = datos.getRowAt(randomIndex);
            clusters.add(filaAleatoria.getData());
        }
    }

    private List<Row> buscarGruposPuntos(Integer cluster){
        return asignacionClusters.get(cluster);
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
    public Integer estimate(Row row){
        double minDistancia = Double.MAX_VALUE;
        int numRepresentante = 0;
        for (int j = 0; j < numClusters; j++){
            double distancia = calculateDistance(row.getData(), clusters.get(j));
            if (distancia < minDistancia) {
                minDistancia = distancia;
                numRepresentante = j + 1;
            }
        }
        return numRepresentante;
    }

}
