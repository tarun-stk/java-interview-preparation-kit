package threads.types.of.locks.explicit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private int count = 0;
    /*Read write locking example:
     * use this when working with read and write operations
     * benefits:
     * allows multiple threads to do read operations, until or unless no write lock is acquired
     * once write lock is acquired, then readlock cannot be acquired
     * idea is when we are only doing read operation, why should we block other threads which are only doing read opn
     * internally write lock and read lock communicate if any of them is locked. */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        /*once write lock is acquired no readlock is given, and only one thread can acquire write lock at any given time*/
        writeLock.lock();
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented");
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        /*NOTE: multiple threads can acquire readlock, until or unless no write lock is acquired*/
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get count: " + count);
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++)
                    example.getCount();
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++)
                    example.increment();
            }
        };

        Thread writerThread = new Thread(writeTask, "writerThread");
        Thread readThread1 = new Thread(readTask, "readerThread1");
        Thread readThread2 = new Thread(readTask, "readerThread2");

        writerThread.start();
        readThread1.start();
        readThread2.start();

        try {
            writerThread.join();
            readThread1.join();
            readThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

/*sample output:
* readerThread2 get count: 0
readerThread1 get count: 0
writerThread incremented
writerThread incremented
writerThread incremented
writerThread incremented
writerThread incremented
writerThread incremented
writerThread incremented
writerThread incremented
writerThread incremented
writerThread incremented
readerThread2 get count: 10
readerThread1 get count: 10
readerThread1 get count: 10
readerThread2 get count: 10
readerThread1 get count: 10
readerThread2 get count: 10
readerThread1 get count: 10
readerThread2 get count: 10
readerThread1 get count: 10
readerThread2 get count: 10
readerThread2 get count: 10
readerThread2 get count: 10
readerThread2 get count: 10
readerThread2 get count: 10
readerThread1 get count: 10
readerThread1 get count: 10
readerThread1 get count: 10
readerThread1 get count: 10*/