package threads.types.of.locks.explicit;

import threads.types.of.locks.explicit.BankAccount;

public class MyThread {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                bankAccount.withdraw(50);
            }
        };
        Thread thread1 = new Thread(task, "Thread1");
        Thread thread2 = new Thread(task, "Thread2");

        thread1.start();
        thread2.start();

    }
}

/*sample output:
*Thread1 attempting to withdraw 50
Thread2 attempting to withdraw 50
Thread1 proceeding with withdrawal
Thread2 could not acquire lock, will try later
Thread1 completed withdrawal. Remaining balance: 50 */
