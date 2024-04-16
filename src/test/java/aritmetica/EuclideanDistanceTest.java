package aritmetica;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {
    @Test
    void EuclideanDisteance(){

        EuclideanDistance distancia = new EuclideanDistance();

        List<Double> ej1 = List.of(2.3, 4.5, 3.8);
        List<Double> ej2 = List.of(6.8, 4.2, 1.7);
        List<Double> ej3 = List.of(6.8, 4.2);
        List<Double> ej4 = List.of(0.5, 7.8, 12.6, 2.6);
        List<Double> ej5 = List.of(3.2, 4.9, 2.6, 1.8);
        List<Double> ej6 = List.of(5.2);
        List<Double> ej7 = List.of();
        List<Double> ej8 = List.of(5.2, 7.4);
        List<Double> ej9 = List.of();
        assertEquals(4.974, distancia.calculateDistance(ej1, ej2), 0.001);
        assertEquals(3.577, distancia.calculateDistance(ej3, ej8), 0.001);
        assertEquals(10.786, distancia.calculateDistance(ej4, ej5), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej3, ej4));
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej1, ej7));
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej2, ej6));
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej8, ej2));
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej7, ej6));
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej7, ej9));


    }

}