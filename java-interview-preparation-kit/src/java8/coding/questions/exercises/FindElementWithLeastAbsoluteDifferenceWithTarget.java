package java8.coding.questions.exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindElementWithLeastAbsoluteDifferenceWithTarget {
    public static void main(String[] args) {
        findElementWithLeastAbsoluteDifferenceWithTarget(5, Arrays.asList(1,2,3,6,7,8));
    }

    private static void findElementWithLeastAbsoluteDifferenceWithTarget(int target, List<Integer> list) {
//        ifPresentOrElse -> first arg if any val there, else output second arg
        list.stream().min(Comparator.comparingInt(num -> Math.abs(num-target))).ifPresentOrElse(System.out::println, () -> System.out.println("No elements found"));
    }
}
