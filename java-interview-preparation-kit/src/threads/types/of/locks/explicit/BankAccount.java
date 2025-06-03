package threads.types.of.locks.explicit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 100;
    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        /*below tries to acquire lock, if can acquire returns true, else returns immediately false, where in we can write a logic say something like server is busy*/
//        if(lock.tryLock()){
        /*below is same like using synchronized, current thread will keep waiting until it acquires lock*/
//        lock.lock();
        /*try {
        when using below, current which has lock, will be interrupted, and a thread which calls below, will acquire the lock.
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        try {
            /*below tries to acquire lock within 1000ms, if can lock, its ok, else simply returns false*/
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                    try {
                        // simulating a behaviour of some process taking alot of time
                        Thread.sleep(10000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                    } catch (InterruptedException e) {
                    } finally {
                        /*this is the critical thing, always unlock the resources, when done*/
                        lock.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient funds");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock, will try later");
            }
        } catch (InterruptedException e) {
            /*always either rethrow exception or bring thread to interrupted state, in catch block simply logging exception wont help
            * the information wil be lost, and other threads will not know what happened */
            Thread.currentThread().interrupt();
        }
        /*now write any logging if interrupted*/
        if(Thread.currentThread().isInterrupted()){
            System.out.println("Interrupted");
        }
//        }
    }

}
