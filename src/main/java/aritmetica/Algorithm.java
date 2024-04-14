package aritmetica;

import excepciones.KmeansExceptionGruposMayorDatos;
import table.Table;

public interface Algorithm <T extends Table, U, V> {
    void train (T dato) throws KmeansExceptionGruposMayorDatos;
    U estimate (V dato);
}
