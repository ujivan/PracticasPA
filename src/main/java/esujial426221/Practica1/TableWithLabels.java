package esujial426221.Practica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableWithLabels extends Table{
    public Map<String,Integer> labelsToIndex = new HashMap<>();


    public RowWithLabel getRowAt(int n) {
        return (RowWithLabel) super.getRowAt(n);
    }

    public int addRowLabel(String label) {
        if (labelsToIndex.containsKey(label)){
            return labelsToIndex.get(label);
        }
        int index = labelsToIndex.size() + 1;
        labelsToIndex.put(label, index);
        return index;
    }
}
