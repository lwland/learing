package learn.lwl.current.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CacliBarrierTest {
    private final Board mainBoard;
    private final CyclicBarrier barrier;

    public CacliBarrierTest(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        barrier=new CyclicBarrier(count,()->{

        });
    }
}
