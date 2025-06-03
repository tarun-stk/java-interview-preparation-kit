package threads.techniques;

import java.util.concurrent.TimeUnit;

public class SecondTechnique {
    public static void main(String[] args) {
        System.out.println("Main thread started");
        /*Starting thread using start method*/
        new SecondTask().start();
        Thread t = new SecondTask();
        t.start();
        System.out.println("Main thread ended");

    }
}

class SecondTask extends Thread {

    static int count;
    int id;

    public SecondTask(){
        this.id = ++ count;
    }

    @Override
    public void run(){
        for(int i = 1; i <= 10; i ++){
            System.out.println("<Task" + id + ">" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

