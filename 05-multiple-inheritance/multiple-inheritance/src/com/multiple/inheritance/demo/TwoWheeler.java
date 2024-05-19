package com.multiple.inheritance.demo;

public class TwoWheeler implements BackWheel, FrontWheel{
    @Override
    public void rotate() {
        System.out.print("rotating");
    }
}
