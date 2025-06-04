package threads.techniques.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UsingFixedThreadPool {
    public static void main(String[] args) {
        System.out.println("Started main thread");
        /*Using newFixedThreadPool we always tell to create fixed number of threads
        * and use only those threads by recycling and reusing them
        * if any task comes when all threads are busy, then it will stored in task queue
        * and when one thread finishes its task, it will pick new pending tasks from queue*/
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 1; i <= 6; i ++){
            executorService.execute(new ThirdTask());
        }
        System.out.println("Ended main thread");
        /*If below line not mentioned the program will keep running*/
        executorService.shutdown();
    }
}
class ThirdTask implements Runnable {
;
    static int count;
    int id;

    public ThirdTask() {
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
        System.out.println("**** <TASK-" + id +"> Completed ****");
    }
}

/*Output when submitting three tasks:*/
    /*Started main thread
        Ended main thread
    #### <TASK-1> Started ####
    #### <TASK-2> Started ####
    #### <TASK-3> Started ####
    <2>TICK TICK - 1
    <1>TICK TICK - 1
    <3>TICK TICK - 1
    <1>TICK TICK - 2
    <2>TICK TICK - 2
    <2>TICK TICK - 3
    <3>TICK TICK - 2
    <2>TICK TICK - 4
    <1>TICK TICK - 3
    <3>TICK TICK - 3
    <3>TICK TICK - 4
    <1>TICK TICK - 4
    <3>TICK TICK - 5
    <2>TICK TICK - 5
    <1>TICK TICK - 5
    **** <TASK-3> Completed ****
    **** <TASK-1> Completed ****
    **** <TASK-2> Completed *****/

/*Output when submitting six tasks:*/
/*You see below we only running three threads at single moment of time
* only when one thread completed its task, new tasks which were waiting in task queue
* were picked up, and started running*/
    /*Started main thread
    Ended main thread
    #### <TASK-3> Started ####
    #### <TASK-1> Started ####
    #### <TASK-2> Started ####
    <3>TICK TICK - 1
    <2>TICK TICK - 1
    <1>TICK TICK - 1
    <3>TICK TICK - 2
    <2>TICK TICK - 2
    <3>TICK TICK - 3
    <1>TICK TICK - 2
    <2>TICK TICK - 3
    <2>TICK TICK - 4
    <1>TICK TICK - 3
    <3>TICK TICK - 4
    <1>TICK TICK - 4
    <1>TICK TICK - 5
    <2>TICK TICK - 5
    **** <TASK-1> Completed ****
    #### <TASK-4> Started ####
    <4>TICK TICK - 1
    <4>TICK TICK - 2
    <3>TICK TICK - 5
    <4>TICK TICK - 3
    **** <TASK-2> Completed ****
    #### <TASK-5> Started ####
    <5>TICK TICK - 1
    <4>TICK TICK - 4
    **** <TASK-3> Completed ****
    #### <TASK-6> Started ####
    <6>TICK TICK - 1
    <5>TICK TICK - 2
    <5>TICK TICK - 3
    <5>TICK TICK - 4
    <6>TICK TICK - 2
    <6>TICK TICK - 3
    <4>TICK TICK - 5
    **** <TASK-4> Completed ****
    <5>TICK TICK - 5
    **** <TASK-5> Completed ****
    <6>TICK TICK - 4
    <6>TICK TICK - 5
    **** <TASK-6> Completed *****/