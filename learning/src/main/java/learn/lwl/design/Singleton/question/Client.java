package learn.lwl.design.Singleton.question;

public class Client {
    public static void main(String[] args) {
        TicketMaker ticketMaker = TicketMaker.getInstance();
        System.out.println(ticketMaker.getNextTicketNum());
        System.out.println(ticketMaker.getNextTicketNum());
        Triple triple1 = Triple.getInstance(0);
        System.out.println(triple1);
        Triple triple2 = Triple.getInstance(1);
        System.out.println(triple2);
        Triple triple3 = Triple.getInstance(2);
        System.out.println(triple3);
        Triple triple4 = Triple.getInstance(2);
        System.out.println(triple4);

    }


}
