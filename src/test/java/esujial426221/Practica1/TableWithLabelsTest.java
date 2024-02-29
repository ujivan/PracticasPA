package esujial426221.Practica1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
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
    void nombreEtiquetas() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"iris.csv";
        Scanner tabla = new Scanner(new File(rutaFichero));

        TableWithLabels tablaMetodo  = CSV.readTableWithLabel(rutaFichero);

        assertEquals(tablaMetodo.getRowAt(1), tablaMetodo.labelsToIndex.get("setosa"));
    }


}