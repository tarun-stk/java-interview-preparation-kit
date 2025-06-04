package threads.deadlock;

/*Below is a good example of deadlock in java
* it happens when two threads are waiting to release their locks
* t1 waiting for t2 to release
* and t2 is waiting for t1 to release
* this situation is deadlock and it never ends*/
public class DeadLockDemo {

    /*Locking is only available on object level, so using String*/
    String s1 = "Tarun";
    String s2 = "Kumar";

    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (s1){
                /*t1 locked s1*/
                System.out.println(Thread.currentThread().getName() + "locked s1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                /*t1 waiting for t2 to release lock on s2*/
                synchronized (s2){
                    System.out.println(s1 + s2);
                }
            }
        }
    });
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (s2){
                /*t2 locked s2*/
                System.out.println(Thread.currentThread().getName() + "locked s2");
                /*t2 waiting for t1 to release lock on s1*/
                synchronized (s1){
                    System.out.println(s1 + s2);
                }
            }
        }
    });

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        deadLockDemo.t1.start();
        deadLockDemo.t2.start();
    }
}
