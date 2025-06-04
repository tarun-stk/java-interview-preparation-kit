package threads.volatilevariables;


/*Notes on volatile:

    Whenever you declare any variable with volatile, it will always store it in main memory, not in cache or other places
    While using local variables, they might be stored in cache or local storage, so when a thread reads the value
    of it, thread might not read up to date value because cache might not be upto date.
    While volatile variables are always read from main memory, thread reads upto date value at any time.*/

/*Whenever dealing with variables which may lead to inconsistent results then use volatile
* If you also need mutual exclusion then go with synchronization
* volatile will only provide you with memory visibility
* where as synchronization will give both memory visibility(volatile) and atomicity(synchronization)*/

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

//    play around by making this non volatile variable
    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    System.out.println("In  while");
                }

            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;

    }
}
