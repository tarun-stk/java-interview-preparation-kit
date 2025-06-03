package threads.objectlevel.locking.withnolock;

class Display {
    public void displayThreadName(String threadName) {
        for (int i = 0; i < 3; i++) {
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

public class ObjectLevelNoLock {

    public static void main(String[] args) throws Exception {
        Display d1 = new Display();

        MyThread t1 = new MyThread(d1, "t1");
        MyThread t2 = new MyThread(d1, "t2");
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

/*Sample output:
//main thread in running state
Main thread
//t1 come to runnable
current state of t1: RUNNABLE
// immediately t2 comes to runnable and running
Current thread: t2
// main thread
current state of t2: BLOCKED
// t1
Current thread: t1
// t2
Current thread: t2
Current thread: t2
// t1
Current thread: t1
Current thread: t1*/
