package AprendizajeAutomatico;

import Table.Row;
import Table.RowWithLabel;
import Table.TableWithLabels;

import java.util.List;
import Aritmetica.*;

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
            double distancia = CalculoDistancias.metricaEuclidiana(row.getData(), sample);
            if (distancia < minDistancia) {
                minDistancia = distancia;
                nClase = rowWithLabel.getNumberClass();
            }
        }
        // Se le suma 1 para que empiezen las clases por el numero 1 y no el 0
        return nClase + 1;
    }
}
