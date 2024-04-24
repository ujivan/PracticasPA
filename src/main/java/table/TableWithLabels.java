package table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableWithLabels extends Table{
    private Map<String,Integer> labelsToIndex = new HashMap<>();
    private int num =  0;
    private List<RowWithLabel> dataWithLabel = new ArrayList<>();

    public Map<String,Integer> getLabelsToIndex(){
        return labelsToIndex;
    }
    public List<RowWithLabel> getDataWithLabel(){
        return dataWithLabel;
    }



    public Integer size(Table datos){
        return super.size(datos);
    }

    public RowWithLabel getRowAt(int n) {
        return (RowWithLabel) super.getRowAt(n);
    }


    public void addRowWhithLabel(List<Double> row, int numero){
        dataWithLabel.add(new RowWithLabel(row, numero));

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
