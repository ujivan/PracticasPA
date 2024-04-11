package Aritmetica;

import Excepciones.KmeansExceptionGruposMayorDatos;
import Table.Table;

public interface Algorithm <T extends Table, U, V> {
    void train (T dato) throws KmeansExceptionGruposMayorDatos;
    U estimate (V dato);
}
