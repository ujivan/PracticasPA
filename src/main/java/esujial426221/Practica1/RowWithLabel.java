package esujial426221.Practica1;

import java.util.List;

public class RowWithLabel extends Row{
    int numberClass;

    public int getNumberClass() {
        return numberClass;
    }

    public RowWithLabel (List<Double> lista, int numero){
        super(lista);
        numberClass = numero;
    }

}
