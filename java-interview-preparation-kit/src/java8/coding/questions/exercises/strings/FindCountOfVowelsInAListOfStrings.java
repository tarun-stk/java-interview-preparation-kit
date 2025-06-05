package java8.coding.questions.exercises.strings;

import java.util.List;

public class FindCountOfVowelsInAListOfStrings {
    public static void main(String[] args) {
        System.out.println(findCountOfVowelsInAListOfStrings(List.of("apple","banana","grape","orange")));
    }

    private static long findCountOfVowelsInAListOfStrings(List<String> list) {
        String vowels = "aeiouAEIOU";
        return list.stream().flatMap(str -> str.codePoints().mapToObj(x -> (char)x)).filter(ch -> vowels.contains(String.valueOf(ch))).count();
    }
}
