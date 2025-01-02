package com.theclcode.bitoperations.numberof1bits;

public class NumberOf1Bits {

    public static void main(String[] args) {
        int[] input = {5, 1, 16, 20, 101};

        for(int i : input) {
            System.out.println("Value: " + i + "(" + Integer.toBinaryString(i) + ")");
            System.out.println("Recursive: " + hammingWeight(i));
            System.out.println("Iterative: " + hammingWeightIterative(i));
            System.out.println();
        }

    }

    public static int hammingWeight(int n) {
        return n == 0 ? 0 : (n&1) + hammingWeight(n >> 1);
    }

    public static int hammingWeightIterative(int n) {
        int count = 0;
        while(n > 0) {
            count += (n & 1);
            n = n >> 1;
        }
        return count;
    }

}
