package threads.techniques.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UsingCachedThreadPool {
    public static void main(String[] args) {
        System.out.println("Started main thread");
        /*The Executors.newCachedThreadPool() method in Java is a factory method provided by the Executors
        class to create a thread pool that creates new threads as needed but will reuse previously constructed
        threads when they are available.*/
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 6; i++) {
            executorService.execute(new SecondTask());
        }
        System.out.println("Ended main thread");
        /*If below line not mentioned the program will keep running*/
        executorService.shutdown();
    }
}

class SecondTask implements Runnable {

    static int count;
    int id;

    public SecondTask() {
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

/*Sample output:*/
    /*Started main thread
    Ended main thread
    #### <TASK-5> Started ####
    #### <TASK-4> Started ####
    #### <TASK-6> Started ####
    #### <TASK-1> Started ####
    #### <TASK-3> Started ####
    #### <TASK-2> Started ####
    <6>TICK TICK - 1
    <5>TICK TICK - 1
    <4>TICK TICK - 1
    <1>TICK TICK - 1
    <2>TICK TICK - 1
    <3>TICK TICK - 1
    <2>TICK TICK - 2
    <3>TICK TICK - 2
    <1>TICK TICK - 2
    <4>TICK TICK - 2
    <1>TICK TICK - 3
    <3>TICK TICK - 3
    <1>TICK TICK - 4
    <6>TICK TICK - 2
    <1>TICK TICK - 5
    <4>TICK TICK - 3
    <2>TICK TICK - 3
    <2>TICK TICK - 4
    <5>TICK TICK - 2
    <4>TICK TICK - 4
    <3>TICK TICK - 4
    **** <TASK-1> Completed ****
    <6>TICK TICK - 3
    <2>TICK TICK - 5
    <4>TICK TICK - 5
    <3>TICK TICK - 5
    <5>TICK TICK - 3
    **** <TASK-4> Completed ****
    **** <TASK-2> Completed ****
    <6>TICK TICK - 4
    <5>TICK TICK - 4
    **** <TASK-3> Completed ****
    <6>TICK TICK - 5
    <5>TICK TICK - 5
    **** <TASK-5> Completed ****
    **** <TASK-6> Completed *****/