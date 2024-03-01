package esujial426221.Practica1;

import java.util.List;

public class KNN {
    private TableWithLabels tdata;
    public void train(TableWithLabels data) {
        this.tdata = data;
    }

    public Integer estimate(List<Double> sample) {
        if (sample.isEmpty() || sample.size() != 4) {
            throw new IllegalArgumentException("La lista tiene que tener un tamaño de 4");
        }
        // Le damos a minDistancia el máximo valor que puede alcanzar un double para que así la primera distancia que compare sea siempre menor
        double minDistancia = Double.MAX_VALUE;
        Integer nClase = null;

        // Cambiamos de la clase row que necesita el bucle for para cada fila a rowWithLabel para que pueda devolver el numero de la clase
        for (Row row : tdata.rows) {
            RowWithLabel rowWithLabel = (RowWithLabel) row;
            double distancia = metricaEuclidiana(row.getData(), sample);
            if (distancia < minDistancia) {
                minDistancia = distancia;
                nClase = rowWithLabel.getNumberClass();
            }
        }
        // Se le suma 1 para que empiezen las clases por el numero 1 y no el 0
        return nClase + 1;
    }

    public double metricaEuclidiana(List<Double> data1, List<Double> data2) {
        if (data1.size() != data2.size()) {
            throw new IllegalArgumentException("Las dos listas tienen que tener el mismo tamaño");
        }

        if (data1.isEmpty() || data2.isEmpty()) {
            throw new IllegalArgumentException("No se puede calcular la metrica sin valores en alguna o las dos listas");
        }

        double suma = 0.0;
        for (int i = 0; i < data1.size(); i++) {
            double resta = data1.get(i) - data2.get(i);
            suma += Math.pow(resta, 2);
        }

        return Math.sqrt(suma);
    }
}
