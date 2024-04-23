package table;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void numFilasTable() {

        /*
        String rutaFichero = "archivos"+ File.separator+"miles_dollars.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));
        int contadorfilas = 0;

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            contadorfilas++;
        }
        tabla.close();

        Table tablaMetodo = CSV.readTable(rutaFichero);
        assertEquals(tablaMetodo.size(tablaMetodo), contadorfilas);

         */
        Table tabla = new Table();
        List<Double> ej1 = List.of(1.8, 1.8, 1.8);
        List<Double> ej2 = List.of(2.7, 2.7, 2.7);

        tabla.addRow(ej1);
        tabla.addRow(ej2);

        assertEquals(tabla.size(tabla), 2);


    }
    @Test
    void columnasTable() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"miles_dollars.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        int contadorColumnas = 0;

        String linea = tabla.nextLine();
        contadorColumnas = linea.split(",").length;

        tabla.close();
        Table tablaMetodo = CSV.readTable(rutaFichero);
        assertEquals(tablaMetodo.sizeHeaders(tablaMetodo), contadorColumnas);

    }

    @Test
    void headersTable() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"miles_dollars.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        Table tablaMetodo = CSV.readTable(rutaFichero);

        List<String> listaHeaders = new ArrayList<>();
        String primeraLinea = tabla.nextLine();

        String[] cabeceras = primeraLinea.split(",");
        listaHeaders.addAll(List.of(cabeceras));

        tabla.close();
        assertEquals(tablaMetodo.getHeaders(tablaMetodo), listaHeaders);

    }

    @Test
    void recuperarContenidoFilasTable() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"miles_dollars.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        Table tablaMetodo = CSV.readTable(rutaFichero);
        int contador = 0;

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            List<Double> almacenLinea = new ArrayList<>();
            if (contador >= 1){
                for (String dato : List.of(linea.split(","))){
                    almacenLinea.add(Double.valueOf(dato));
                }
                Row fila = tablaMetodo.getRowAt(contador-1);
                assertEquals(almacenLinea, fila.getData());
            }

            contador++;
        }
        tabla.close();

    }



}