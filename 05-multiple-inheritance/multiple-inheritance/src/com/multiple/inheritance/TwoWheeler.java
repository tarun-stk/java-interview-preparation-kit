package com.multiple.inheritance;

public class TwoWheeler implements BackWheel, FrontWheel{
    @Override
    public void rotate() {
        System.out.print("rotating");
    }
}
