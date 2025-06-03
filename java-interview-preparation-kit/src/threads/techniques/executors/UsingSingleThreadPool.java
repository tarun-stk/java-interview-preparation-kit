package threads.techniques.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UsingSingleThreadPool {
    public static void main(String[] args) {
        System.out.println("Started main thread");
        /*Only created and maintains one thread at any moment of time,
        reuses it all the time, executes tasks sequentially in the order they arrive.*/
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 3; i++) {
            executorService.execute(new SecondTask());
        }
        System.out.println("Ended main thread");
        /*If below line not mentioned the program will keep running
        * Shuts down gracefully meaning will complete all running tasks and will stop accepting
        * any new incoming tasks*/
        executorService.shutdown();
        /*Where as below shutsdown immediately by stopoing all current running tasks also.*/
//        executorService.shutdownNow();
    }
}

class FirstTask implements Runnable {

    static int count;
    int id;

    public FirstTask() {
        this.id = ++count;
    }

    @Override
    public void run() {
        System.out.println("#### <TASK-" + id + "> Started ####");
        for (int i = 1; i <= 5; i++) {
            System.out.println("<" + id + ">TICK TICK - " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("**** <TASK-" + id + "> Completed ****");
    }
}

/*Samople output:*/
/*    Started main thread
    Ended main thread
    #### <TASK-1> Started ####
    <1>TICK TICK - 1
    <1>TICK TICK - 2
    <1>TICK TICK - 3
    <1>TICK TICK - 4
    <1>TICK TICK - 5
    **** <TASK-1> Completed ****
    #### <TASK-2> Started ####
    <2>TICK TICK - 1
    <2>TICK TICK - 2
    <2>TICK TICK - 3
    <2>TICK TICK - 4
    <2>TICK TICK - 5
    **** <TASK-2> Completed ****
    #### <TASK-3> Started ####
    <3>TICK TICK - 1
    <3>TICK TICK - 2
    <3>TICK TICK - 3
    <3>TICK TICK - 4
    <3>TICK TICK - 5
    **** <TASK-3> Completed *****/
