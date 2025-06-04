package threads.techniques;

import java.util.concurrent.TimeUnit;

public class FirstTechnique {
    public static void main(String[] args) {
        System.out.println("Main thread started");
        new FirstTask();
        Thread t = new FirstTask();
        System.out.println("Main thread ended");

    }
}

class FirstTask extends Thread {

    static int count;
    int id;

    public FirstTask(){
        this.id = ++ count;
        /*Starting thread using by calling run method*/
        this.run();
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
