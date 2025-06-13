package threads.executors.framework;

public class TraditionalMultiThreading {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        /*Below is synchronous way using only single thread
         * sample output:
         * Total time taken: 9076*/
        for (int i = 1; i < 10; i ++){
            long result = factorial(i);
            System.out.println(result);
        }
        System.out.println("Total time taken: " + (System.currentTimeMillis() - startTime));

        /*Total time taken: 1007, using multiple threads, time got decreased but
        * but here, we're creation and management threads, also, at times it can become
        * very error prone, when working with multiple threads
        * also not reusing threads
        * executor framework helps here, by creating and managing threads, so that dev can only focus on businsess logic*/
        Thread[] threads = new Thread[9];
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            threads[i - 1] = new Thread(
                    () -> {
                        long result = factorial(finalI);
                        System.out.println(result);
                    }
            );
            threads[i - 1].start();
        }

//        make sure all threads are completed exec, before printing time taken

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {

            }
        }
        //        below line will be printed by main thread, to print accurate time taken, make sure all processses are finished
        System.out.println("Total time taken: " + (System.currentTimeMillis() - startTime));
    }

    private static long factorial(int num) {
        int result = 1;
        try {
//            simulating an computationally heavy work being done, by doing below
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
