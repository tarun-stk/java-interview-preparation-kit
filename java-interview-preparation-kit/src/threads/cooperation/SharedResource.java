package threads.cooperation;

public class SharedResource {

    private boolean hasData;
    private int data;

    public SharedResource(){
        hasData = false;
    }

    synchronized void consume(){
        while(!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " consumed data: " + data);
        hasData = false;
        notify();
    }

    synchronized void produce(int value){
//Always use while instead of if, because there isn't guarantee that this thread will go into waiting state immediately, it is always in hands of
//        thread scheduler, to keep this in waiting, and notify other thread.
        while(hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        System.out.println(Thread.currentThread().getName() + " produced data: " + data);
        hasData = true;
        notify();
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    resource.consume();
                }
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    resource.produce(i);
                }
            }
        };
        Thread consumer = new Thread(task1, "Consumer");
        Thread producer = new Thread(task2, "Producer");

        consumer.start();
        producer.start();

    }
}
//sample output:
//Producer produced data: 0
//Consumer consumed data: 0
//Producer produced data: 1
//Consumer consumed data: 1
//Producer produced data: 2
//Consumer consumed data: 2
//Producer produced data: 3
//Consumer consumed data: 3
//Producer produced data: 4
//Consumer consumed data: 4
//Producer produced data: 5
//Consumer consumed data: 5
//Producer produced data: 6
//Consumer consumed data: 6
//Producer produced data: 7
//Consumer consumed data: 7
//Producer produced data: 8
//Consumer consumed data: 8
//Producer produced data: 9
//Consumer consumed data: 9