package esujial426221.Practica1;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableWithLabels extends Table{
    public Map<String,Integer> labelsToIndex = new HashMap<>();

    int num =  0;
    public List<RowWithLabel> rows = new ArrayList<>();

    public void addRowLabel(RowWithLabel row) {
        rows.add(row);
    }



    public RowWithLabel getRowAt(int n) {
        return (RowWithLabel) super.getRowAt(n);
    }

    public int search(String label){
        if (labelsToIndex.containsKey(label)){
            return labelsToIndex.get(label);
        }
        if (addLabel(label)){
            return labelsToIndex.get(label);
        } else {
            throw new RuntimeException();
        }
    }

    public boolean addLabel (String label){
        if (labelsToIndex.containsKey(label)){
            return false;
        }
        labelsToIndex.put(label, num);
        num++;
        return true;
    }
    public void addRowWithLabel(List<Double> linea, int numero){
        this.rows.add(new RowWithLabel(linea, numero));
    }
}
