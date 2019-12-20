import java.util.concurrent.CountDownLatch;

/**
 * @author : Rocher Kong
 * @date: 2019/12/19 19:09
 */
public class CountDownBatchQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread thread1=new Thread(new FirstBatchWorker(cdl));
            thread1.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread thread2=new Thread(new SecondBatchWorker(cdl));
            thread2.start();
        }
        while(cdl.getCount()!=0){
           System.out.println("wait....");
            Thread.sleep(1000);
        }
        System.out.println("wait for first batch finish!");
    }
}

class FirstBatchWorker implements Runnable {
    private CountDownLatch countDownLatch;
    public FirstBatchWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("First batch executed!");
        countDownLatch.countDown();
    }
}
class SecondBatchWorker implements Runnable {
    private CountDownLatch countDownLatch;
    public SecondBatchWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Second batch executed!");
        countDownLatch.countDown();
    }
}
