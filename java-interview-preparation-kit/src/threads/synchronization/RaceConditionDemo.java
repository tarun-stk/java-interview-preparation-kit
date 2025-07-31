package threads.synchronization;


//Notes on synchronization
//Whenever a synchronized keyword is added to a method / block, it means there is a lock
//and only one particular thread can obtain a lock over it, other threads cannot access the synchronized block
//unless or until the current thread leaves the lock
//leaving lock scenarios:
//  when thread completes its synchronized block
//  when thread encounters an exception
//How synchronization works:
//    Locking is as per object but not per class, when there are multiple synchronized methods, and one
//      thread is executing synchronized method x1, then lock is obtained on that particular object,
//        it means when one more thread wants to access synchronized method x2, it cannot enter it because
//        lock was on object not on method, so it can start running only when current thread leaves x1.
//      Also when current thread is executing synchronized method, other threads can execute non-synchronized methods
//            of same object with no problem.
//Always use same lock when working with same instance critical data

//Whenever there is a lock and other thread tries to access the same, then it goes into blocked state.
//Like that multiple threads would get into blocked state
//After releasing lock, thread scheduler will decide which thread would again obtain a lock

public class RaceConditionDemo {

    public static void main(String[] args) {

//        creating task
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(100);

        Thread john = new Thread(bankAccount);
        Thread anita = new Thread(bankAccount);

        john.setName("John");
        anita.setName("Anita");

        john.start();
        anita.start();


//        Sample output:
//        John is about to withdraw
//        John completed withdrawal
//        Sorry, Not enough balance to withdraw for Anita
    }
}

class BankAccount implements Runnable{

    private int balance;
    public void setBalance(int balance){
        this.balance = balance;
    }
    @Override
    public void run(){
        makeWithDrawal(75);
        if(balance < 0){
            System.out.println("Money overdrawn");
        }
    }

    private synchronized void makeWithDrawal(int amount) {
        if(amount <= balance){
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal");
        }
        else{
            System.out.println("Sorry, Not enough balance to withdraw for " + Thread.currentThread().getName());
        }
    }

    /*whenever dealing with variables you must synchronize wherever it is used
    * like for example getBalance() is not syncd and makeWithDrawal is syncd
    * then when John tells anita to deposit money, like shown below it is not syncd
    *
    * private void makeDeposit(int amount){
//            balance += amount;
//    }
*   and when anita making deposit, john can still enter makeWithdrawal as the lock not being obtained
* at object level, anita thread might leave makeDeposit without actually updating balance, to give john
* thread execution time, then john gets insufficient funds error
*
*
* below could be good implementation of this scenario
* private void synchronized makeDeposit(int amount){
//            balance += amount;
//    }*/
}
