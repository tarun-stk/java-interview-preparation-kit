package com.java.interview.prep.functionalInterfaces;

@FunctionalInterface // completely optional annotation
//if you have only one abstract method, it by default becomes functional interface.
public interface Print {

    abstract void print(String s);

    default String returnSomeString(){
        return "I'm coming from returnSomeString()";
    }

    static void printSomething(){
        System.out.println("\n printing from static method printSomething() in functional interface");
    }
}
