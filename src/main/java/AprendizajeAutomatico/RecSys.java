package AprendizajeAutomatico;

import Aritmetica.Algorithm;
import Excepciones.KmeansExceptionGruposMayorDatos;
import Table.Table;
import Table.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecSys{

    Algorithm algorithm;
    Map<String, Integer> mapaEstimaciones = new HashMap<>();

    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    void train (Table trainData) throws KmeansExceptionGruposMayorDatos {
        algorithm.train(trainData);
    }

    void run(Table testData, List<String> testItemNames) {
        for (Row row : testData.rows) {
            int contadorLinea = 0;
            Integer numEst = (Integer) algorithm.estimate(row.data);
            mapaEstimaciones.put(testItemNames.get(contadorLinea), numEst);
            contadorLinea++;
        }

    }


}


