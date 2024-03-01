package esujial426221.Practica1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {

    @Test
    void numFilaTableWithLabels() throws FileNotFoundException {
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
    void columnasTableWithLabels() throws FileNotFoundException {
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
    void headersTableWithLabels() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        TableWithLabels tablaMetodo = CSV.readTableWithLabel(rutaFichero);

        List<String> listaHeaders = new ArrayList<>();
        String primeraLinea = tabla.nextLine();

        String[] cabeceras = primeraLinea.split(",");
        //almacenamos la primera linea de la tabla la variabe listaHeeaders para posteriormente compararla
        listaHeaders.addAll(List.of(cabeceras));

        tabla.close();
        assertEquals(tablaMetodo.headers, listaHeaders);

    }
    @Test
    void numFilasAsignadaTableWithLabels() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));
        String label = "";
        String[] almacenLinea;
        int contador = 0;
        int numLinea = 0;

        TableWithLabels tablaMetodo = CSV.readTableWithLabel(rutaFichero);

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            almacenLinea = linea.split(",");
            if (contador == 1){
                // si nos encontramos en la primera linea, almacenamos la etiqueta, y la comparamos
                label = almacenLinea[almacenLinea.length-1];
                assertEquals(tablaMetodo.labelsToIndex.get(label), numLinea);

            } else if (contador > 1 && !label.equals(almacenLinea[almacenLinea.length-1]) ){
                // si la linea mayor que 1, y se cambia la etiqueta; almacenamos la nueva etqueta y la comparamos
                numLinea++;
                label = almacenLinea[almacenLinea.length-1];
                assertEquals(tablaMetodo.labelsToIndex.get(label), numLinea);

            }
            contador++;

        }
        tabla.close();
    }

    @Test
    void recuperarContenidoFilasTableWithLabels() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));
        TableWithLabels tablaMetodo = CSV.readTableWithLabel(rutaFichero);

        int contador = 0;

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            List<Double> almacenLinea = new ArrayList<>();
            List<String> lineaAux = Arrays.asList(linea.split(","));
            // Almacenamos cada dato de cada linea en almacenLinea, exceptuando la primera linea
            if (contador >= 1){
                for (int i = 0; i < lineaAux.size()-1 ; i++){
                    almacenLinea.add(Double.valueOf(lineaAux.get(i)));
                }
                Row fila = tablaMetodo.rows.get(contador-1);
                assertEquals(almacenLinea, fila.getData());
            }
            contador++;
        }
        tabla.close();

    }





}