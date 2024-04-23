package table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableWithLabels extends Table{
    public Map<String,Integer> labelsToIndex = new HashMap<>();

    int num =  0;

    public void addRow(List<Double> linea, int numero) {
        super.addRow(linea);
    }

    public Integer size(Table datos){
        return super.size(datos);
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

    public boolean addLabel(String label){
        if (labelsToIndex.containsKey(label)){
            return false;
        }
        labelsToIndex.put(label, num);
        num++;
        return true;
    }

}
