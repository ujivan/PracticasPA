package esujial426221.Practica1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {

    @Test
    void NumeroFilaTableWithLabels() throws FileNotFoundException {
        String rutaFichero = "archivos"+ File.separator+"milles_dollar.cvs";
        Scanner tabla = new Scanner(new File(rutaFichero));
        int contadorfilas = 0;

        while (tabla.hasNextLine()){
            String linea = tabla.nextLine();
            contadorfilas++;
        }
        tabla.close();

        Table tablaMetodo = CSV.readTableWithLabel(rutaFichero);
        assertEquals(tablaMetodo.headers.size()+1, contadorfilas);
    }


}