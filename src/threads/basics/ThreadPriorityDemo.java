package threads.basics;

public class ThreadPriorityDemo {

    public static void main(String[] args) {

//        this references to itself, and return a toString() output
//        like shown below
//        Thread[main, 5, main]
//        decoding params
//        main -> thread name
//        5 -> priority of thread, default value is 5
//        main -> thread group -> obselette
        System.out.println(Thread.currentThread());

        Thread t1 = new Thread(new EmailCampaign()); //new state
        Thread t2 = new Thread(new DataAggregator());

//        setting names to threads
        t1.setName("EmailCampaign");
        t2.setName("DataAggregator");

//        setting priorities
//        but finally it's in the hands of scheduler to give priority or  not
//        but mostly scheduler will lean towards thread which has max priority
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

//        Starting threads will go into runnable state.
        t1.start();
        t2.start();

        try {
//            This means the current thread(main) will keep itself on hold until the t2 completes its execution
            t2.join();
//            This means the current thread(main) will keep itself on hold upto 10 ms and if t2 doesn't complete
//            its execution within 10ms then main thread will gain back cpu time and goes into running state.
            t2.join(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        main thread
        System.out.println("In main...");
    }
}

//Task
class EmailCampaign implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i <= 10; i ++){
//            prints name of thread.
            System.out.println(Thread.currentThread().getName());
            if(i == 5){
//                Hints to scheduler that thread is willing to yield its current use of cpu
//                But finally it depends on scheduler to retain or revoke its cpu time
                Thread.currentThread().yield();
            }
        }
    }
}

//Task
class DataAggregator implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i <= 10; i ++){
//            prints name of thread.
            System.out.println(Thread.currentThread().getName());
        }
    }
}

