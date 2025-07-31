package threads.rececondition;


//About the class
//Anita and john are married couple and are sharing a mutual bank account
//let's assume they both make transaction of 75 at same time
//As in multi threading environment, we cannot guarantee which thread gains cpu time at any given time
//We might encounter a case where John first enters makeWithDrawal() and passes the condition balance >= amount
//immediately after that John might go back into runnable state, giving Anita cpu time
//now Anita goes into makeWithDrawal() and as John left without updating the balance, the balance still remained 100
//now Anita also able to withdraw amount, and updates balance to 25, and completes execution
//now John resumes where he left off, as condition was passed previously, it is not valid now
//he also makes transaction and updates balance to -50, and completes execution
//This above case is known as race condition, Whenever you're working with concurrency (multi threading) utmost care should be taken
//The above case would work perfectly fine in single threaded environment

public class RaceConditionDemo {

    public static void main(String[] args) {

//        creating task
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(100);

        /*Two threads workign on same task*/
        Thread john = new Thread(bankAccount);
        Thread anita = new Thread(bankAccount);

        john.setName("John");
        anita.setName("Anita");

        john.start();
        anita.start();


//        Sample output:
//        Anita is about to withdraw
//        John is about to withdraw
//        Anita completed withdrawal
//        Money overdrawn
//        John completed withdrawal
//        Money overdrawn
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

    private void makeWithDrawal(int amount) {
        if(amount <= balance){
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal");
        }
        else{
            System.out.println("Not enough balance to withdraw for " + Thread.currentThread().getName());
        }
    }
}
