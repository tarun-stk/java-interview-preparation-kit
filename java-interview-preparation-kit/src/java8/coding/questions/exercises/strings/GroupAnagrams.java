package java8.coding.questions.exercises.strings;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagrams {
    public static void main(String[] args) {
        groupAnagrams(Arrays.asList("listen", "silent", "enlist", "hello", "ohlle"));
    }

    private static void groupAnagrams(List<String> list) {
        list.stream().collect(Collectors.groupingBy(string -> {
            return string.chars().mapToObj(ch -> (char)ch).sorted().map(ch -> ch+"").collect(Collectors.joining(""));
        })).entrySet().stream().map(Map.Entry::getValue).forEach(System.out::println);
    }
}
