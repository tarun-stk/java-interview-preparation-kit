package threads.reentrant.lock.with.no.lock;

/*No synchronization and no locking*/
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }

}

class Runner{
    private int count = 0;
    public void increment(){
        for(int i = 0; i < 10000; i ++){
            count ++;
        }
    }
    public void firstThread(){
        increment();
    }
    public void secondThread(){
        increment();
    }
    public void finished(){
        System.out.println("Count is: " +count);
        /*sample output:
        * Count is: 12255*/
    }
}