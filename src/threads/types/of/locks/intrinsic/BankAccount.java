package threads.types.of.locks.intrinsic;

public class BankAccount {

    private int balance = 100;

    /*Drawbacks of using synchronized
    * when working with multiple threads
    * if using synchronized only one thread can execute below block of code at any given moment of time
    * other threads must wait until current execution is completed, in a situation where if current thread is
    * taking alot of time we don't have any timeout logic in place, we shouldn't let other threads wait indefinitely
    * instead we should say that current thread is in execution try to come after sometime, this kind of mechanism is not
    * possible when working with intrinsic locks (synchronized)*/
    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
            try {
//                simulating a behaviour of some process taking alot of time
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
        }
        else{
            System.out.println(Thread.currentThread().getName() + " insufficient funds");
        }
    }


}
