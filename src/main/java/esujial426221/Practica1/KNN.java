package esujial426221.Practica1;

import java.util.List;

public class KNN {
    private TableWithLabels tdata;
    //SI
    public void train(TableWithLabels data) {
        this.tdata = data;
    }

    public Integer estimate(List<Double> sample) {

        double minDistancia = Double.MAX_VALUE;
        Integer nClase = null;

        for (Row row : tdata.rows) {
            RowWithLabel rowWithLabel = (RowWithLabel) row;
            double distancia = metricaEuclidiana(row.getData(), sample);
            if (distancia < minDistancia) {
                minDistancia = distancia;
                nClase = rowWithLabel.getNumberClass();
            }
        }
        return nClase;
    }

    private double metricaEuclidiana(List<Double> data1, List<Double> data2) {
        if (data1.size() != data2.size()) {
            throw new IllegalArgumentException();
        }

        double suma = 0.0;
        for (int i = 0; i < data1.size(); i++) {
            double resta = data1.get(i) - data2.get(i);
            suma += Math.pow(resta, 2);
        }

        return Math.sqrt(suma);
    }
}
