package threads.types.of.locks.explicit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*benefits of using reentrant lock
* acquire lock
* unlock
* try lock
* deadlock prevention*/
public class ReentrantExample {

    private final Lock lock = new ReentrantLock();

    void outerMethod(){
//        calling lock on outer method
        try{
            lock.lock();
            System.out.println("Outer method");
            innerMethod();
        }
        finally {
//            after execting lock counter will go to 0 from 1.
            lock.unlock();
        }


    }

    void innerMethod(){
        try{
            System.out.println("Inner method");
//            again locking in inner method, this will cause a dead lock because am waiting to lock until i release the lock which is acquired in outer method in usual cases, but reentrant lock is special,
//            if lock is called multiple times on same thread, it will simply lock and maintain a lock counter
            lock.lock();
        }
        finally{
//            locking in inner should also unlock the thread, but using reentrant lock, the lock counter will be used, which simpy decrements the lock counter
//            when unlocked, and if counter goes to zero only then the whole lock will be released, in this case, lock coutner was 2, now after execting below line
//            it will become 1.
            lock.unlock();
        }
    }

}
