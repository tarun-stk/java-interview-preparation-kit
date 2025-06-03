package threads.atomicvariables;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*Notes on atomic variables
 *   These provide both memory visibility and synchronization without locking
 *   synchronization has locking so it is expensive as it has to do block -> runnable -> running stages when it is being locked
 *   by using atomic variables we can avoid all those costly steps involved above*/
public class MeetUpEventSimulator {

    static private class MeetUpEvent {
        //        name of event
        private String name;
        //        setting initial count to 1(organizer itself)
        private AtomicInteger count = new AtomicInteger(1);

        public MeetUpEvent(String name) {
            this.name = name;
        }

        public void attending(int guestCount) {
            if (guestCount == 1) {
                count.incrementAndGet();
            } else {
                count.addAndGet(guestCount);
            }
        }

        public void notAttending(int guestCount) {
            if (guestCount == 1) {
                count.decrementAndGet();
            } else {
                boolean isUpdated = false;

//                below updates value without applying any locking mechanism
                while (!isUpdated) {
                    int currentCount = count.get();
                    int nextCount = currentCount - guestCount;

                    isUpdated = count.compareAndSet(currentCount, nextCount);
                }
            }
        }

        public int getTotalCount() {
            return count.get();
        }


    }

    public static void main(String[] args) {

        MeetUpEvent jugBuston = new MeetUpEvent("Java Boston Group");
        Thread User1 = new Thread(new Runnable() {
            @Override
            public void run() {
                jugBuston.attending(4);
                System.out.println(Thread.currentThread().getName() + " : " + jugBuston.getTotalCount());
            }
        });
        Thread User2 = new Thread(new Runnable() {
            @Override
            public void run() {
                jugBuston.attending(3);
                System.out.println(Thread.currentThread().getName() + " : " + jugBuston.getTotalCount());
                jugBuston.notAttending(3);
                System.out.println(Thread.currentThread().getName() + " : " + jugBuston.getTotalCount());
            }
        });
        Thread User3 = new Thread(new Runnable() {
            @Override
            public void run() {
                jugBuston.attending(1);
                System.out.println(Thread.currentThread().getName() + " : " + jugBuston.getTotalCount());
            }
        });

        User3.setName("User 3");
        User2.setName("User 2");
        User1.setName("User 1");

        User1.start();
        sleep(1);
        User2.start();
        sleep(2);
        User3.start();
        sleep(3);

        System.out.println("Total attending: " + jugBuston.getTotalCount());
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ex) {

        }
    }

}
