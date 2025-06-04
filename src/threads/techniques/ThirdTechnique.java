package threads.techniques;

import java.util.concurrent.TimeUnit;

/*Cr eating thread by implementing runnable interface*/
public class ThirdTechnique {
    public static void main(String[] args) {
        System.out.println("Main thread started");
        new Thread(new ThirdTask()).start();
        Thread t = new Thread(new ThirdTask());
        t.start();
        System.out.println("Main thread ended");

    }
}

class ThirdTask implements Runnable {

    static int count;
    int id;

    public ThirdTask() {
        this.id = ++count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("<Task" + id + ">" + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

