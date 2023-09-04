package com.java.interview.prep.implementation;

import com.java.interview.prep.functionalInterfaces.Add;

public class AddImpl {

    public static void main(String[] args) {
//        having more than one line so adding body inside {}
        Add ad = (x) -> {
            int sum = 0;
            for(int i: x)
                sum += i;
            return sum;
        };

//        calling abstract method add
        int add1 = ad.add(3,4,54,2,2,4,54,23); //add1: 146
        System.out.println("add1: " + add1);

//        calling again with dif params
        int add2 = ad.add(1, 3);
        System.out.println("add2: " + add2); //add2: 4

//        Calling our static method:
        Add.printSomething(); //printing from static method printSomething() in functional interface

//        calling our default method
//        To call default method we must override the interface and then create an object
//        of that clas and then call using object.
    }
}
