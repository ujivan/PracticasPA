package ReadFiles;
import table.Table;


public abstract class ReaderTemplate {
    private String source;

    public ReaderTemplate(String source) {
        this.source = source;
    }
    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();

    abstract String getNextData();

    protected abstract Table createTable();

    public final Table readTableFromSource() {

        openSource(source);
        processHeaders(getNextData());

        while (hasMoreData()) {
            processData(getNextData());
        }
        closeSource();

        return createTable();
    }
}
