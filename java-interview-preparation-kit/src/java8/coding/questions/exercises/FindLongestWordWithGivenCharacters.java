package java8.coding.questions.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindLongestWordWithGivenCharacters {
    public static void main(String[] args) {
        List<Character> list = new ArrayList<>();
        list.add('o');
        list.add('e');
        list.add('n');
        list.add('t');
        List<String> words = Arrays.asList("to", "tyuopiwk", "toe", "toen", "toeen", "toten");

        words.stream().filter(word -> list.stream().allMatch(ch -> word.indexOf(ch) >= 0)).max(Comparator.comparingInt(String::length)).ifPresentOrElse(System.out::println, () -> System.out.println("no string found"));
    }
}
