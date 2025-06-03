package threads.synchronization;

public class SynchronizationWithDifferentLocks {

    public static void main(String[] args) {

//        creating task
        BankAccount1 bankAccount = new BankAccount1();
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


class BankAccount1 implements Runnable{

    private int balance;
    public void setBalance(int balance){
        this.balance = balance;
    }
    @Override
    public void run(){
        if(Thread.currentThread().getName().equals("John"))
            makeWithDrawal(150);
        if(Thread.currentThread().getName().equals("Anita"))
            makeDeposit(50);
        if(balance < 0){
            System.out.println("Money overdrawn");
        }
    }

    private void makeDeposit(int amount){
        synchronized ("s"){
            balance += amount;
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
}

//In the above scenario the concept of locking is lost
//As for makeWithdrawal is workign on locking for current object
//makeDeposit is working with different synchronized lock (a string)
//Let's suppose there;s no enough balance in account, and John asks anita to deposit some amount
//And they are in hurry, both start operations at same time
//Anita enters makeDeposit and before updates balance, john enters makewithdrawal as there are two diff locks,
//This is possible both accessing diff locks, not same locks
//and before updating the balanec itself john tries withdrawing again, and fails to withdraw as balance is still less.
//So whenever working with same instance critical variables, use same lock

//The above can corrected using

//    private void makeDeposit(int amount){
//        synchronized (this){
//            balance += amount;
//        }
//    }

//OR
//
//    private synchronized void makeDeposit(int amount){
//        balance += amount;
//    }

