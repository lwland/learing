package learn.lwl.design.chain_of_responsibility;

public class OddSupport extends Support {
    public OddSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNum() % 2 == 1) {
            return true;
        }
        return false;
    }
}
