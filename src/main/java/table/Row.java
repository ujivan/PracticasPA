package table;


import java.util.ArrayList;
import java.util.List;

public class Row {
    public List<Double> data;

    public List<Double> getData() {
        return data;
    }

    public Row(List<Double> lista){
        this.data = new ArrayList<>(lista);
    }
}
