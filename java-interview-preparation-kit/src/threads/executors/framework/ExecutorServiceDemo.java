package threads.executors.framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        /*Executors: utility class, provides util methods, to create executorservice
         * below creates three threads, and reuses them once they become free, without creating new threads*/
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        below creates a thread pool of varying size, means it creates threads as and when needed
//        it's of dynamic size thread pool, and if any thread is not being reused within 60 secs, it will
//        terminate it, use this approach when you're working with variable load and when tasks are short lived
//        short lived because, if the tasks are long living, and however we've variable number of threads
//        if many such long lived tasks are coming, then many threads will be created and keep on running leading
//        to poor performance.
        ExecutorService cachedServiec = Executors.newCachedThreadPool();
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);
            });
        }

        /*when using ExecutorService extending Executor, in which you only have one method called execute
         * using which you can execute tasks, but as there are no methods supporting shutdown, sysetm will keep on running
         * that's why we'll not prefer below kind*/
        /*Executor executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                long result = factorial(finalI);
                System.out.println(result);
            });
        }*/

//        below waits until all processes are completed by the current executor & also it will stop accepting new tasks, once completed, it will get shutdown
//        if not shutdown, then app will keep running, because executor can be reusable, it thinks new task might be coming.
        executorService.shutdown();
//        below same like above, but it will not wait until all the tasks are submitted, it immediately shuts down.
//        executor.shutdownNow();
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
