package recomendacionCanciones;

import aritmetica.Algorithm;
import excepciones.KmeansExceptionGruposMayorDatos;
import table.Table;
import table.Row;
import java.util.*;


public class RecSys implements Algorithm<Table, Integer, List<Double>> {

    Algorithm algorithm;
    Map<Integer, Integer> mapaEstimaciones = new HashMap<>();

    private List<String> nombreCanciones;

    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void train (Table trainData) throws KmeansExceptionGruposMayorDatos {
        algorithm.train(trainData);
    }

    @Override
    public Integer estimate(List<Double> data) {
        return (Integer) algorithm.estimate(data);
    }

    public void run(Table testData, List<String> testItemNames) {
        nombreCanciones = testItemNames;
        if (testData == null || testItemNames == null) {
            throw new IllegalArgumentException("Los datos de prueba o los nombres de los elementos son nulos");
        }

        for (int i = 0; i < testData.size(testData); i++) {
            Row row = testData.getRowAt(i);
            Integer numEst = estimate(row.getData());
            mapaEstimaciones.put(i, numEst);
        }
    }

    private int findName(String nameItem){
        for (int i = 0; i < nombreCanciones.size(); i++){
            if (nombreCanciones.get(i).equals(nameItem)){
                return i;
            }
        }
        return -1;
    }

    public List<String> recommend(String NameLinkedItem, int numRecommendations)  {

        int posicion = findName(NameLinkedItem);

        if(posicion == -1){
            throw new IndexOutOfBoundsException();
        }

        Integer etiqueta = mapaEstimaciones.get(posicion);

        List<String> recomendaciones = new ArrayList<>();
        for (int i = 0; i < mapaEstimaciones.size(); i++){
            if (mapaEstimaciones.get(i) == etiqueta){
                recomendaciones.add(nombreCanciones.get(i));
                if (numRecommendations == recomendaciones.size()-1){
                    break;
                }
            }
        }
        return recomendaciones;
    }


}


