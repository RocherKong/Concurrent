import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author : Rocher Kong
 * @date: 2019/12/18 17:52
 */
public class CountDownLatchDemo {
    public static void main(String[] args){
        final java.util.concurrent.CountDownLatch latch=new java.util.concurrent.CountDownLatch(2);
        System.out.println("主线程开始执行…… ……");
        ThreadFactory threadFactory;
        ExecutorService es1= Executors.newCachedThreadPool();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        ExecutorService es2= Executors.newCachedThreadPool();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es2.shutdown();

        System.out.println("等待两个线程执行完毕…… ……");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");


    }
}
