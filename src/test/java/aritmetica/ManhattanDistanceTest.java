package aritmetica;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanDistanceTest {

    @Test
    void ManhattanDistance(){

        ManhattanDistance distancia = new ManhattanDistance();
        List<Double> ej1 = List.of(2.0, 2.0, 2.0);
        List<Double> ej2 = List.of(1.0, 1.0, 1.0);
        List<Double> ej3 = List.of(4.0, 4.0, 4.0);
        List<Double> ej4 = List.of(5.0, 5.0);
        List<Double> ej5 = List.of();

        assertEquals(3, distancia.calculateDistance(ej1, ej2));
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej3, ej4));
        assertThrows(IllegalArgumentException.class, ()-> distancia.calculateDistance(ej3, ej5));
    }

}