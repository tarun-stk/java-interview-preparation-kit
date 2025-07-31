package threads.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintEvenOddUsingExecutors {
    int count = 0;
    int max;
    private Object lock = new Object();

    public PrintEvenOddUsingExecutors(int max) {
        this.max = max;
    }


    void printOdd() throws InterruptedException {
        synchronized (lock) {
            while (count <= max) {
                if (count % 2 == 0) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " " + count);
                count ++;
                lock.notify();
            }
        }
    }

    void printEven() throws InterruptedException {
        synchronized (lock) {
            while (count <= max) {
                if (count % 2 != 0) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " " + count);
                count ++;
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        PrintEvenOddUsingExecutors printEvenOddUsingExecutors = new PrintEvenOddUsingExecutors(10);
        Runnable odd = () -> {
            try {
                printEvenOddUsingExecutors.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        Runnable even = () -> {
            try {
                printEvenOddUsingExecutors.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        executorService.submit(even);
        executorService.submit(odd);
        executorService.shutdown();
    }
}
