package com.java.interview.prep.functionalInterfaces;

public interface Add {

//    methods in interfaces are by default public abstract, so these two keywords are optional

//    using var args -> variable length arguments. User can pass any number of args.
    int add(int ... a); // by default public abstract,so functional interface.

    default String returnSomeString(){
        return "I'm coming from returnSomeString()";
    }

    static void printSomething(){
        System.out.println("\nprinting from static method printSomething() in functional interface");
    }
}
