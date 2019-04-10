package learn.lwl.design.chain_of_responsibility;

public class LimitSupport extends Support {
    private int limit;

    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNum() < limit) {
            return true;
        }
        return false;
    }
}
