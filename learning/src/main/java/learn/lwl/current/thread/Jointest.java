package learn.lwl.current.thread;

public class Jointest {
    public static void main(String[] args) {
        Thread preThread = Thread.currentThread();
        for (int i = 1; i < 10; i++) {
            Thread thread = new Thread(new JoinThread(preThread));
            preThread = thread;
            thread.start();
        }
    }

    static class JoinThread implements Runnable {
        private Thread preThread;

        public JoinThread(Thread preThread) {
            this.preThread = preThread;
        }

        @Override
        public void run() {
            try {
                preThread.join();
                System.out.println(Thread.currentThread() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
