package learn.lwl.design.obeserver;

public class IncreaseNumGenerator extends NumGenerator {
    private int num = 0;

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public void execute() {
        for (int i = 0; i < 50; i++) {
            num = i;
            notifyObserver();
        }
    }
}
