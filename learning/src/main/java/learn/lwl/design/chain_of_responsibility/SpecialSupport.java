package learn.lwl.design.chain_of_responsibility;

public class SpecialSupport extends Support {
    private int special;

    public SpecialSupport(String name, int special) {
        super(name);
        this.special = special;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNum() == special) {
            return true;
        }
        return false;
    }
}
