package esujial426221.Practica1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void filasTest() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"milles_dollar.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));
        int contadorfilas = 0;

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            contadorfilas++;
        }
        tabla.close();

        Table tablaMetodo = CSV.readTable(rutaFichero);
        assertEquals(tablaMetodo.rows.size()+1, contadorfilas);
    }
    @Test
    void columnasTest() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"milles_dollar.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        int contadorColumnas = 1;
        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            contadorColumnas = linea.length();

        }
        Table tablaMetodo = CSV.readTable(rutaFichero);
        assertEquals(tablaMetodo.headers.size()+1, contadorColumnas);

    }

    @Test
    void headersTest() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"milles_dollar.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        Table tablaMetodo = CSV.readTable(rutaFichero);

        List<String> listaHeaders = new ArrayList<>();
        String primeraLinea = tabla.nextLine();

        String[] cabeceras = primeraLinea.split(" ");
        listaHeaders.addAll(List.of(cabeceras));

        for (int i = 0; i < tablaMetodo.headers.size(); i++){
            assertEquals(tablaMetodo.headers.get(i), listaHeaders.get(i));
        }
    }

}