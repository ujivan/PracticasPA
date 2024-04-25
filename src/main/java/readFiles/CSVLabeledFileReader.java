package readFiles;

import table.RowWithLabel;
import table.TableWithLabels;
import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader{
    private TableWithLabels table = new TableWithLabels();

    public CSVLabeledFileReader(String source) {
        super(source);
    }

    @Override
    public void processData(String data) {
        List<String> output = List.of(data.split(","));
        List<Double> outputDouble = new ArrayList<>();
        for (int i = 0; i < output.size()-1; i++) {
            outputDouble.add(Double.valueOf(output.get(i)));
        }
        String label = output.get(output.size() - 1);
        int labelRef = table.search(label);
        RowWithLabel row = new RowWithLabel(outputDouble, labelRef);
        table.addRow(row);
    }

    @Override
    protected TableWithLabels createTable() {
        return table;
    }
}
