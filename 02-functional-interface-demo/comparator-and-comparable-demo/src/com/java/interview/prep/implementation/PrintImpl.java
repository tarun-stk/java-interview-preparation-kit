package com.java.interview.prep.implementation;

import com.java.interview.prep.functionalInterfaces.Print;

public class PrintImpl {
//Traditional kind of overriding abstract methods..
//    @Override
//    public void print(String s) {
//        System.out.println("Traditional overriding the abstract method");
//    }


        public static void main(String[] args) {
            String print = "Lambda implementation";

            Print p = (a) -> System.out.println(a); // //output: Lambda implementation
            p.print(print);
//        one more example:
            Print p1 = (String b) -> { //when you've only one param, data type declaration
//            is optional
                String output = b.toUpperCase();
                System.out.println(output);
            }; // when you have more than one line then enclose body with in {}

//        calling fid1;
            p1.print("tarun kumar soodula"); //output: TARUN KUMAR SOODULA


        }
}
