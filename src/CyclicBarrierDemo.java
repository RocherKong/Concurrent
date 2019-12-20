import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : Rocher Kong
 * @date: 2019/12/19 19:27
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Action Go");
            }
        });
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicBarrierWorker(cb));
            t.start();
        }
    }
    static class CyclicBarrierWorker implements Runnable {
        private CyclicBarrier barrier;
        CyclicBarrierWorker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                for (int i = 0; i < 9; i++) {
                    System.out.println("No."+i+".Executed!");
                    barrier.await();
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
