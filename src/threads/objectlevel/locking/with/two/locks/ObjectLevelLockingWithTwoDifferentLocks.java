package threads.objectlevel.locking.with.two.locks;


class Display {
    synchronized void displayThreadName(String threadName) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Current thread: " + threadName);
        }
    }

}

class MyThread extends Thread {
    Display d;
    String threadName;

    public MyThread(Display d, String threadName) {
        this.threadName = threadName;
        this.d = d;
    }

    @Override
    public void run() {
        d.displayThreadName(threadName);
    }
}
public class ObjectLevelLockingWithTwoDifferentLocks {
    public static void main(String[] args) throws Exception {
        Display d1 = new Display();
        Display d2 = new Display();

//        using two different locks
        MyThread t1 = new MyThread(d1, "t1");
        MyThread t2 = new MyThread(d2, "t2");
        t1.start();
        t2.start();

        while (t1.isAlive()) {
            System.out.println("Main thread");
            System.out.println("current state of t1: " + t1.getState());
            System.out.println("current state of t2: " + t2.getState());
            Thread.sleep(2000);
        }
    }
}
