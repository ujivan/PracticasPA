package aprendizajeAutomatico;

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
        if (sample.isEmpty() ) {
            throw new IllegalArgumentException();
        }
        // Le damos a minDistancia el máximo valor que puede alcanzar un double para que así la primera distancia que compare sea siempre menor
        double minDistancia = Double.MAX_VALUE;
        Integer nClase = null;

        for (int i = 0; i < tdata.size(tdata); i++){
            RowWithLabel rowWithLabel = tdata.getRowAt(i);
            double distancia = calculateDistance(rowWithLabel.getData(), sample);
            if (distancia < minDistancia) {
                minDistancia = distancia;
                nClase = rowWithLabel.getNumberClass();
            }
        }
        return nClase;
    }
}
