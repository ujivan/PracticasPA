package table;

import java.util.ArrayList;
import java.util.List;

public class Table{
    private List<String> headers = new ArrayList<>();
    private List<Row> rows = new ArrayList<>();


    public Row getRowAt(int rowNumber) {
        return rows.get(rowNumber);
    }
    public List<Row> getRow(){
        return rows;
    }
    public List<String> getHeaders(){
        return headers;
    }


    public void addRow(Row row) {
        rows.add(row);
    }

    public void addHeaders(List<String> cabeceras){
        this.headers = cabeceras;
    }


    public Integer size(Table datos){
        return rows.size();
    }
    public Integer sizeHeaders(Table datos){
        return headers.size();
    }


}
