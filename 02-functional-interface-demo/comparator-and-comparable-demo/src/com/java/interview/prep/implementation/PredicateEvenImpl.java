package com.java.interview.prep.implementation;

import com.java.interview.prep.functionalInterfaces.PredicateEven;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicateEvenImpl {

    public static void main(String[] args) {
        PredicateEven pe = (number) -> {
            if(number%2 == 0)
                return true;
            return false;
        };
//        One liner
//        PredicateEven pe = (number) -> number%2 == 0;

        System.out.println("pe.test(5001): " + pe.test(5001)); //pe.test(5001): false

//        Predicate exmple
//        Predicate -> Functional Interface which has abstract method as test
        Predicate<Integer> pred = (a) -> a%2 == 0;

        List<Integer> listOfNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .collect(Collectors.toList());
        listOfNumbers.stream().filter(pred).forEach(System.out::println); //2, 4, 6, 8.

    }
}
