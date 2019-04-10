package learn.lwl.design.decorator;

public class UpdownBorder extends Border {
    private char aChar;

    public UpdownBorder(Display display, char aChar) {
        super(display);
        this.aChar = aChar;
    }

    @Override
    public int getColumns() {
        return display.getColumns();
    }

    @Override
    public int getRows() {
        return 1 + display.getRows() + 1;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {
            return makeLine(aChar, getColumns());
        } else if (row == display.getRows() + 1) {
            return makeLine(aChar, getColumns());
        } else {
            return display.getRowText(row - 1);
        }
    }

    private String makeLine(char ch, int count) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            stringBuffer.append(ch);
        }
        return stringBuffer.toString();
    }
}
