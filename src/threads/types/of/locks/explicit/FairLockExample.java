package threads.types.of.locks.explicit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample {

    /*should pass true as param to enable fairness of locking mechanism. now all threads will acquire locks in FIFO technique, which ensures fairness by giving locks to all threads*/
    private final Lock lock = new ReentrantLock(true);

    public void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairLockExample unFairLockExample = new FairLockExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                unFairLockExample.accessResource();
            }
        };
        Thread thread1 = new Thread(task, "Thread1");
        Thread thread2 = new Thread(task, "Thread2");
        Thread thread3 = new Thread(task, "Thread3");

        thread1.start();
        thread2.start();
        thread3.start();

        /*sample output
        * Thread1 acquired the lock.
Thread1 released the lock.
Thread2 acquired the lock.
Thread2 released the lock.
Thread3 acquired the lock.
Thread3 released the lock
*
* Above you see threads acquire locks in arbitrary manner, like all of a sudden bunch of people fighting for tickets, not following any Q. there may be some thread, which never gets
* access to lock, which is unfair.
*/
    }

}
