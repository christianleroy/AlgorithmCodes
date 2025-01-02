package com.theclcode.bitoperations.binaryaddition;

// AKA Sum of Two Array in Leetcode
public class BinaryAddition {

    public static void main(String[] args) {
        int a = -5;
        int b = -5;
        int c;

        do {
            c = a & b; // Get all 1+1 = 0 carry 1
            a = a ^ b; // Get all 1+0 = 1
            b = c << 1; //Shift all carry 1s to the right
        } while (b!=0); //While there are carry overs, continue. Use c != 0 if positive only.

        System.out.println(a);
    }

}
