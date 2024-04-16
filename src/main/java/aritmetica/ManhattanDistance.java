package aritmetica;

import java.util.List;

public class ManhattanDistance implements Distance{
    public double calculateDistance(List<Double> data1, List<Double> data2) {
        if (data1.size() != data2.size()) {
            throw new IllegalArgumentException("Las dos listas tienen que tener el mismo tamaño");
        }

        if (data1.isEmpty() || data2.isEmpty()) {
            throw new IllegalArgumentException("No se puede calcular la metrica sin valores en alguna o las dos listas");
        }

        double suma = 0.0;
        for (int i = 0; i < data1.size(); i++) {
            suma += data1.get(i) - data2.get(i);
        }

        return suma;
    }
}
