package threads.reentrant.lock.with.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*Using reentrant locks we can avoid using sunchronization, we can use mthods like lock and unlock from Lock
* interface*/
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }
}

class Runner {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    /*THis is dangerous situation because you obtained lock on increment method, what if that mthod
     * throws exception then this lock can be never be released, so always put unlock method in finally block*/
    public void firstThread() {
        try {
            /*similar to obtaining lock to increment method
            * similar to making increment method as synchronized block*/
            lock.lock();
            increment();
        } finally {
            lock.unlock();
        }

    }

    public void secondThread() {
        try {
            lock.lock();
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
        /*sample output:
         * Count is: 20000*/
    }
}