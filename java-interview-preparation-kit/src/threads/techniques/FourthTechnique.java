package threads.techniques;

import java.util.concurrent.TimeUnit;

public class FourthTechnique {
    public static void main(String[] args) {
        System.out.println("Main thread started");
        /*Creating thread using anonymous thread*/
        new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i = 1; i <= 10; i ++){
                    System.out.println("<Task1>" + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i = 1; i <= 10; i ++){
                    System.out.println("<Task2>" + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t.start();
        System.out.println("Main thread ended");

    }
}

