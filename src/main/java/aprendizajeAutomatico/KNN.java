package aprendizajeAutomatico;

import table.Row;
import table.RowWithLabel;
import table.TableWithLabels;

import java.util.List;
import aritmetica.*;

public class KNN implements Algorithm<TableWithLabels, Integer, List<Double>>, Distance {
    private TableWithLabels tdata;
    Distance distance;

    public KNN(Distance distance) {
        this.distance = distance;
    }

    public double calculateDistance(List<Double> data1, List<Double> data2) {
        return distance.calculateDistance(data1, data2);
    }
    @Override
    public void train(TableWithLabels data) {
        this.tdata = data;
    }

    @Override
    public Integer estimate(List<Double> sample) {
        if (sample.isEmpty() || sample.size() != 4) {
            throw new IllegalArgumentException("La lista tiene que tener un tamaño de 4");
        }
        // Le damos a minDistancia el máximo valor que puede alcanzar un double para que así la primera distancia que compare sea siempre menor
        double minDistancia = Double.MAX_VALUE;
        Integer nClase = null;

        // Cambiamos de la clase row que necesita el bucle for para cada fila a rowWithLabel para que pueda devolver el numero de la clase
        for (int j = 0; j < tdata.size(tdata); j++){
            RowWithLabel rowWithLabel = (RowWithLabel) tdata.getRowAt(j);
            double distancia = calculateDistance(tdata.getRowAt(j).getData(), sample);
            if (distancia < minDistancia) {
                minDistancia = distancia;
                nClase = rowWithLabel.getNumberClass();
            }

        }
        // Se le suma 1 para que empiezen las clases por el numero 1 y no el 0
        return nClase + 1;
    }
}
