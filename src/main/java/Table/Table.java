package Table;

import java.util.ArrayList;
import java.util.List;

public class Table{
    public List<String> headers = new ArrayList<>();
    public List<Row> rows = new ArrayList<>();

    public Row getRowAt(int rowNumber) {
        return rows.get(rowNumber);
    }
    public void addRow(List<Double> linea) {
        rows.add(new Row(linea));
    }

    public void addHeaders(List<String> cabeceras){
        headers = cabeceras;
    }

}
