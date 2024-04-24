package table;


import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Double> data;

    public List<Double> getData() {
        return data;
    }

    public Row(List<Double> lista){
        data = new ArrayList<>(lista);
    }
}
