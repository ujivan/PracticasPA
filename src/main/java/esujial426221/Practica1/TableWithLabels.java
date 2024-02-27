package esujial426221.Practica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableWithLabels extends Table{
    Map<String,Integer> labelsToIndex = new HashMap<>();


    public RowWithLabel getRowAt(int n) {
        return (RowWithLabel) rows.get(n);
    }

    public int addRowLabel(String label) {
        if (labelsToIndex.containsKey(label)){
            return labelsToIndex.get(label);
        }
        labelsToIndex.put(label, labelsToIndex.size()+1);
        return labelsToIndex.get(label);
    }




}
