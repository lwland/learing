package learn.lwl.design.decorator;

import java.util.ArrayList;
import java.util.List;

public class MultlineStringDisplay extends Display {
    private List<String> list = new ArrayList<>();
    private int columnSize = 0;

    public MultlineStringDisplay() {

    }

    public MultlineStringDisplay(List<String> list, int columnSize) {
        this.list = list;
        this.columnSize = columnSize;
    }

    public void add(String str) {
        this.list.add(str);
        if (str.getBytes().length > columnSize) {
            columnSize = str.getBytes().length;
        }
    }

    @Override
    public int getColumns() {
        return columnSize;
    }

    @Override
    public int getRows() {
        return list.size();
    }

    @Override
    public String getRowText(int row) {
        return list.get(row);
    }
}
