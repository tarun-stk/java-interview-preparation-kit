package threads.executors.framework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        /*below starts the task after 5 seconds of initial delay, it pushes the task immediately into wait queue
         * so scheduler waits until the task is completed*/
        scheduler.schedule(() -> System.out.println("printing after 5 seconds delay"), 5, TimeUnit.SECONDS);

//        below is scheduling at fixed rate, when we do this, the task will not be immediately pushed to wait queue
//        if we shutdown immediately, as there are no tasks in queue, scheduler will shutdown immediately without doing anything
//        to work with scheduleAtFixedRate, we've to properly do this by adding one more schedule to shut it down
//        below runs for every 5 secs, even if the prev task is not completed yet, new one will start right after 5 secs delay
//
        scheduler.scheduleAtFixedRate(
                () -> System.out.println("Task executed after every 5 seconds"),
                5,
                5,
                TimeUnit.SECONDS
        );

//        whereas in below will wait for delay(5) seconds, after the task has completed its exec,
//        once a task is completed, the new/next will start after 5 secs delay.
        scheduler.scheduleWithFixedDelay(
                () -> System.out.println("Task executed after every 5 seconds"),
                5,
                5,
                TimeUnit.SECONDS
        );

        scheduler.schedule(() -> {
                    System.out.println("Initiating shutdown...");
                    scheduler.shutdown();
                },
                20, TimeUnit.SECONDS);
    }
}
