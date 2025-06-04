package threads.exercises;

public class PrintOddEvenUsingTwoThreads {

    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        Thread t1 = new Thread(printNumbers);
        Thread t2 = new Thread(printNumbers);
        t1.setName("even");
        t2.setName("odd");
        t1.start();
        t2.start();
    }

}

class PrintNumbers implements Runnable{
    int count = 0;
    @Override
    public void run(){
        try {
            print();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void print() throws InterruptedException {
        while(count < 21){
            if(count%2 != 0){
                if(Thread.currentThread().getName().equals("even")){
                    wait();
                }
            }
            else{
                if(Thread.currentThread().getName().equals("odd")){
                    wait();
                }
            }
            System.out.println(Thread.currentThread().getName() + " printing: " + count);
            count ++;
            notify();
        }
    }
}
