package java8.coding.questions.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortTheGivenList {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>(Arrays.asList(234, 234, 23, 234, 234, 354, 45, 623, 42, 2, 654, 846846,
				846, 4, 5432, 168, 432, 16, 84, 321, 684));
		numbers.stream().sorted().forEach(System.out::println);

	}

}
