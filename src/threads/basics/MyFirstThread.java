package threads.basics;


//Some important notes on thread
//Thread is a part of process (unit of process) or sub process etc.,
//Multi threading is core feature of java
//Multi threading allows to perform many things at once
//eg: Thread is a worker
//and work is a task(Runnable)
//Even if you don't create any threads in your app, by default there will be always one thread called Main thread
//JVM runs until all the threads complete their execution
//Whenever you run your app, main thread will start running
//And order of execution of threads is decided by Thread Scheduler, and no one can predict the order, there's no guarantee which thread will start
//Thread states
//1. New, 2. Runnable, 3. Running, 4. Waiting/Blocked, 5.Terminated

//Thread class by default implements Runnable interface, which means it is already overriding run() method

//restarting the thread
//once thread done it;s job it will go into dead/terminated state, it will still stay on head memory, but restarting it doesn;t work

import java.util.concurrent.TimeUnit;

public class MyFirstThread {

    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
//        task.run();
//        OR
        thread.start();
//        task.run() and thread.start() does same

//        ways to make thread sleep
        try {
            Thread.sleep(3000); // sleeps for 3 seconds.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        second way
//        TimeUnit an enum provided by Java 5 to work with sleep, benefit of using this is
//        Using thread you can only do sleep method using milliseconds in method param
//        By using TimeUnit you can make sleep using days, hours, minutes. more convenient way of using.
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("In main....");
    }


}

class Task implements Runnable{

    @Override
    public void run() {
        System.out.println("In run....");
        go();
    }

    private void go() {
        System.out.println("In go....");
        more();
    }

    private void more() {
        System.out.println("In more....");
    }
}

