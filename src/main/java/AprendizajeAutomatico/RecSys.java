package AprendizajeAutomatico;

import Aritmetica.Algorithm;
import Excepciones.KmeansExceptionGruposMayorDatos;
import Excepciones.NameNotFoundedException;
import Table.Table;
import Table.Row;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecSys{

    Algorithm algorithm;
    Map<String, Integer> mapaEstimaciones = new HashMap<>();

    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void train (Table trainData) throws KmeansExceptionGruposMayorDatos {
        algorithm.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames) {
        if (testData == null || testItemNames == null) {
            throw new IllegalArgumentException("Los datos de prueba o los nombres de los elementos son nulos");
        }
        mapaEstimaciones.clear();
        int contadorLinea = 0;
        for (Row row : testData.rows) {
            Integer numEst = (Integer) algorithm.estimate(row.data);
            mapaEstimaciones.put(testItemNames.get(contadorLinea), numEst);
            contadorLinea++;
        }
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) throws NameNotFoundedException {
        if (mapaEstimaciones.isEmpty() || !mapaEstimaciones.containsKey(nameLikedItem)) {
            throw new NameNotFoundedException("El nombre no se encuentra en la lista testItemNames o el mapa de estimaciones está vacío.");
        }
        List<String> recomendaciones = new ArrayList<>();
        Integer estimacion = mapaEstimaciones.get(nameLikedItem);
        if (estimacion != null) {
            for (Map.Entry<String, Integer> entry : mapaEstimaciones.entrySet()) {
                Integer value = entry.getValue();
                if (value != null && value.intValue() == estimacion.intValue() && !entry.getKey().equals(nameLikedItem)) {
                    recomendaciones.add(entry.getKey());
                    if (recomendaciones.size() == numRecommendations) {
                        break;
                    }
                }
            }
        } else {
            throw new NullPointerException("La estimación para el elemento dado es nula.");
        }
        return recomendaciones;
    }

}


