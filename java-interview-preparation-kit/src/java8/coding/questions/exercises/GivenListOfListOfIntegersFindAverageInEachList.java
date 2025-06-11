package java8.coding.questions.exercises;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GivenListOfListOfIntegersFindAverageInEachList {
    public static void main(String[] args) {
        List<List<Integer>> list = Arrays.asList(
                List.of(1,2,3),
                List.of(3),
                List.of(20,30),
                List.of(1)
        );

        System.out.println(list.stream().map(subList -> subList.stream().mapToDouble(Integer::doubleValue).average().orElse(0)).collect(Collectors.toList()));
    }
}
