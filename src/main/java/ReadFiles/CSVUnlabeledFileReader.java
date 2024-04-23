package ReadFiles;

import table.Row;
import table.Table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSVUnlabeledFileReader extends ReaderTemplate{

    private Table table = new Table();

    BufferedReader br;

    public CSVUnlabeledFileReader(String source) {
        super(source);
    }

    @Override
    void openSource(String source) {
        try {
            br = new BufferedReader(new FileReader(source));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void processHeaders(String headers) {
        table.addHeaders(Arrays.asList(headers.split(",")));
    }

    @Override
    void processData(String data) {
        String[] output = data.split(",");
        List<Double> outputDouble = new ArrayList<>();
        for (String value : output) {
            outputDouble.add(Double.valueOf(value));
        }
        Row row = new Row(outputDouble);
        table.addRow(row);
    }

    @Override
    void closeSource() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    boolean hasMoreData() {
        try {
            return br.readLine() != null;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    String getNextData() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Table createTable() {
        return table;
    }
}
