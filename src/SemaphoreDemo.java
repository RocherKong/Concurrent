import java.util.concurrent.Semaphore;

/**
 * @author : Rocher Kong
 * @date: 2019/12/18 19:06
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        System.out.println("begin!");
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++)
        {
            Thread thread=new Thread(new SemaphoreWorker(semaphore));
            thread.start();
        }
    }
}
class SemaphoreWorker implements Runnable {
    private String name;
    private Semaphore semaphore;
    public SemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            log("is waiting for a permit!");
            semaphore.acquire();
            Thread.sleep(3000);
            log("acquired a permit!");
            log("executed!");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
    private void log(String msg) {
        if (name == null) {
            name = Thread.currentThread().getName();
        }
        System.out.println(name + " " + msg);
    }
}