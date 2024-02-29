package esujial426221.Practica1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {

    @Test
    void numeroFilaTableWithLabels() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));
        int contadorfilas = 0;

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            contadorfilas++;
        }
        tabla.close();

        TableWithLabels tablaMetodo = CSV.readTableWithLabel(rutaFichero);
        assertEquals(tablaMetodo.rows.size()+1, contadorfilas);
    }

    @Test
    void columnasTableWithLabelsTest() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        int contadorColumnas = 0;

        String linea = tabla.nextLine();
        contadorColumnas = linea.split(",").length;

        tabla.close();
        TableWithLabels tablaMetodo = CSV.readTableWithLabel(rutaFichero);
        assertEquals(tablaMetodo.headers.size(), contadorColumnas);

    }

    @Test
    void headersTableWithLabelsTest() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        TableWithLabels tablaMetodo = CSV.readTableWithLabel(rutaFichero);

        List<String> listaHeaders = new ArrayList<>();
        String primeraLinea = tabla.nextLine();

        String[] cabeceras = primeraLinea.split(",");
        listaHeaders.addAll(List.of(cabeceras));

        assertEquals(tablaMetodo.headers, listaHeaders);

    }
    @Test
    void numeroFilas() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));
        String label = "";
        String[] almacen;
        int contador = 0;
        int numLinea = 0;

        TableWithLabels tablaMetodo = CSV.readTableWithLabel(rutaFichero);

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            almacen = linea.split(",");
            if (contador == 1){
                label = almacen[almacen.length-1];
                assertEquals(tablaMetodo.labelsToIndex.get(label), numLinea);

            } else if (contador > 1){
                if (!label.equals(almacen[almacen.length-1])){
                    numLinea++;
                    label = almacen[almacen.length-1];
                    assertEquals(tablaMetodo.labelsToIndex.get(label), numLinea);
                }
            }
            contador++;

        }
        tabla.close();
    }


}