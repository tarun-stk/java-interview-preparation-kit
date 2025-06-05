package java8.coding.questions.exercises.strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FindFirstLongestWordInAString {
    public static void main(String[] args) {
//        System.out.println(findFirstLongestWordInAString("some random words are coming from lorem ipsum platform"));
        System.out.println(findFirstLongestWordInAString("My name is sankar thota I work at EpamSystems!! as JavaDeveloper"));
    }

    private static String findFirstLongestWordInAString(String s) {
        return Arrays.stream(s.split("\\s")).max(Comparator.comparingInt(String::length)).orElseGet(null);
    }
}
