package java8.coding.questions.exercises;

import java.util.stream.IntStream;

public class FibonacciSeriesUptoN {
    public static void main(String[] args) {
        printFibonacciUntil(10);
    }

    private static void printFibonacciUntil(int n) {
        int[] a = {0}, b = {1};
        IntStream.rangeClosed(1, n).forEach(x -> {
            int temp = a[0] + b[0];
            System.out.print(a[0] + " ");
            a[0] = b[0];
            b[0] = temp;
        });
    }
}
