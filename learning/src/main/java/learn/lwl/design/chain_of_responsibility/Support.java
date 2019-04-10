package learn.lwl.design.chain_of_responsibility;

public abstract class Support {
    protected Support next;
    protected String name;

    public Support setNext(Support support) {
        this.next = support;
        return next;
    }

    public Support(String name) {
        this.name = name;
    }

    public final void support(Trouble trouble) {
//        if (resolve(trouble)) {
//            done();
//        } else if (next != null) {
//            next.support(trouble);
//        } else {
//            failed();
//        }
        Support obj = this;
        while (true) {
            if (obj.resolve(trouble)) {
                obj.done();
                break;
            } else if (obj.next == null) {
                obj.failed();
                break;
            }
            obj = obj.next;
        }

    }

    protected abstract boolean resolve(Trouble trouble);

    protected void done() {
        System.out.println("this trouble has been resolved by" + this);
    }

    private void failed() {
        System.out.println("this trouble has not been resolved");
    }

    @Override
    public String toString() {
        return "Support{name='" + name + '}';
    }
}
