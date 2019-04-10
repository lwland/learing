package learn.lwl.design.Singleton.question;

public class TicketMaker {
    private final static TicketMaker singleton = new TicketMaker();

    public final static TicketMaker getInstance() {
        return singleton;
    }

    private TicketMaker() {

    }

    private int ticketNum = 1000;

    public synchronized int getNextTicketNum() {
        return ticketNum++;
    }
}
