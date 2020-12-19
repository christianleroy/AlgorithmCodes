package com.theclcode.smallproblems.binaryaddition;

public class BinaryAddition {

    public static void main(String[] args) {
        int a = 1024;
        int b = 201;
        int c;

        do {
            c = a & b;
            a = a ^ b;
            b = c << 1;
        } while (c > 0);

        System.out.println(a);
    }

}
