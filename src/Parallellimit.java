import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : Rocher Kong
 * @date: 2019/12/18 18:52
 */
public class Parallellimit {

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(100);
        CountDownLatch cd1=new CountDownLatch(100);
        for (int i=0;i<100;i++)
        {
            CountRunnable runnable=new CountRunnable(cd1);
            pool.execute(runnable);
        }
    }
}

class CountRunnable implements Runnable{
    private CountDownLatch countDownLatch;

    CountRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            synchronized (countDownLatch){
                countDownLatch.countDown();
                System.out.println("thread counts = " + (countDownLatch.getCount()));
            }
            countDownLatch.await();
            System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        finally {

        }

    }
}
