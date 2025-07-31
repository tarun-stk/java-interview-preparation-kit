package threads.exercises;

public class PrintNumbersUsingThreeThreads {
    public static void main(String[] args) {
        PrintNumbersUsingThreads print = new PrintNumbersUsingThreads();
        Thread t1 = new Thread(print);
        Thread t2 = new Thread(print);
        Thread t3 = new Thread(print);

        t1.setName("One");
        t2.setName("Two");
        t3.setName("Three");

        t1.start();
        t2.start();
        t3.start();
    }
}

class PrintNumbersUsingThreads implements Runnable {
    int i = 1;

    @Override
    public void run() {
        try {
//            print();
            print1();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void print() throws InterruptedException {
        for (; i < 7; ) {
//            synchronized (this) {
            if ((i == 1 || i == 4) && (Thread.currentThread().getName().equals("One"))) {
                System.out.println(Thread.currentThread().getName() + ": " + i++);
                notifyAll();
            } else if ((i == 2 || i == 5) && (Thread.currentThread().getName().equals("Two"))) {
                System.out.println(Thread.currentThread().getName() + ": " + i++);
                notifyAll();
            } else if ((i == 3 || i == 6) && (Thread.currentThread().getName().equals("Three"))) {
                System.out.println(Thread.currentThread().getName() + ": " + i++);
                notifyAll();
            } else {
                wait();
            }
//            }
        }
    }

    private synchronized void print1() throws InterruptedException {
        while (i < 20) {
            if (i % 3 == 1 && Thread.currentThread().getName().equals("One")) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                i++;
                notifyAll();
            } else if (i % 3 == 2 && Thread.currentThread().getName().equals("Two")) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                i++;
                notifyAll();
            } else if (i % 3 == 0 && Thread.currentThread().getName().equals("Three")) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                i++;
                notifyAll();
            } else {
                wait();
            }
        }
    }
}
