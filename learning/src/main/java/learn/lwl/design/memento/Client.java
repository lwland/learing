package learn.lwl.design.memento;

public class Client {
    public static void main(String[] args) {
        Gamer gamer = new Gamer(100);
        Memento memento = gamer.createMemento();//保存
        for (int i = 0; i < 100; i++) {
            System.out.println("======" + i + "======");
            System.out.println("当前状态是" + gamer);
            gamer.bet();
            System.out.println("当前状态是" + gamer);
            if (gamer.getMoney() > memento.getMoney()) {
                memento = gamer.createMemento();
            } else {
                gamer.restoreMemento(memento);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("");

        }
    }
}
