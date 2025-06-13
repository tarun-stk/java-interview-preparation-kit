package threads.executors.framework;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        submit returns Future object.
//        Using which you can check the status of the task using some util methods available like isDone, etc.
        Future<?> runnableDemo = executorService.submit(() -> System.out.println("Task is in execution"));
//        Using Callable lambda with submit
        Future<String> callableDemo = executorService.submit(() -> "Hello");

//        runnablle with success message
//        as run method cannot return anything by default, you can explicitly give some result message, so future
//        will give this message once the task is successfully submitted.
        Future<String> submitWithStatusMessage = executorService.submit(() -> System.out.println("submit third method"), "Success");
        try {
//            below prints Success
            System.out.println(submitWithStatusMessage.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        try {
//            below prints Hello
            System.out.println(callableDemo.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
//        returns boolean immediately if the task is done or not.
        System.out.println(runnableDemo.isDone());

        Callable<Integer> callable1 = () -> {
            System.out.println("callable1");
            return 1;
        };
        Callable<Integer> callable2 = () -> {
            System.out.println("callable2");
            return 2;
        };
        Callable<Integer> callable3 = () -> {
            System.out.println("callable3");
            return 3;
        };

        List<Callable> list = Arrays.asList(callable1, callable2, callable3);

        try {
//            invokeAll is used to submit list of tasks
//            and it is special, when invokeAll is invoked, the current thread will get blocked and will wait
//            until all the tasks invoked using invokeAll will be completed.
//            and this method returns list of futures, representing futures of all tasks
            List<Future<Integer>> futures = executorService.invokeAll((Collection)list);
//            below is used when working with time constraints, if all tasks not completed within given time
//            the tasks which are still remaining after time completed, will be cancelled.
//            List<Future<Integer>> futures = executorService.invokeAll(Arrays.asList(callable1, callable2, callable3), 1, TimeUnit.SECONDS);
            for(Future<Integer> future: futures){
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        try {
//            below returns result of any task that is complteted, and once any task completed
//            it will cancel others in execution
            executorService.invokeAny((Collection)list);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


//        calls shutdown
        executorService.shutdown();
//        returns true if shutdown() method is called or false otherwise
        executorService.isShutdown();
//        returns true if shutdown is completed or false otherwise
        executorService.isTerminated();

        try {
            //        waits if task not completed, once completed, returns the result whatever the task has returned;
//            When you call get() method on Runnable submit, however it will not return because of void return type
//            We can still use it to block the thread until the task is completed
//            When used with Callable submit method, it returns what call() method returns
            System.out.println(runnableDemo.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
