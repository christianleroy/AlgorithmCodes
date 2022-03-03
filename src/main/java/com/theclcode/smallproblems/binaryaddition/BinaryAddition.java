package com.theclcode.smallproblems.binaryaddition;

public class BinaryAddition {

    public static void main(String[] args) {
        int a = 1024;
        int b = 201;
        int c;

        do {
            c = a & b; // Get all 1+1 = 0 carry 1
            a = a ^ b; // Get all 1+0 = 1
            b = c << 1; //Shift all carry 1s to the right
        } while (c > 0); //While there are carry overs, continue.

        System.out.println(a);
    }

}
