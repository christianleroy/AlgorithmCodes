package com.theclcode.smallproblems.climbingstairs;

import java.util.Arrays;

//fibonacci
public class ClimbingStairs {
    private Integer[] table;

    public static void main(String[] args) {
        // Output refers to the nth value in the Fibonacci sequence if it starts with 1, 2, 3.. instead of 0, 1, 1, 2...
        printFiboRecursive();
        System.out.println();
        printFiboDp();
    }

    private static void printFiboRecursive() {
        System.out.println(new ClimbingStairs().climbStairs(5));
        System.out.println(new ClimbingStairs().climbStairs(2));
        System.out.println(new ClimbingStairs().climbStairs(0));
        System.out.println(new ClimbingStairs().climbStairs(1));
        System.out.println(new ClimbingStairs().climbStairs(8));
        System.out.println(new ClimbingStairs().climbStairs(3));
    }

    private static void printFiboDp() {
        System.out.println(new ClimbingStairs().climbStairsDp(5));
        System.out.println(new ClimbingStairs().climbStairsDp(2));
        System.out.println(new ClimbingStairs().climbStairsDp(0));
        System.out.println(new ClimbingStairs().climbStairsDp(1));
        System.out.println(new ClimbingStairs().climbStairsDp(8));
        System.out.println(new ClimbingStairs().climbStairsDp(3));
    }


    public int climbStairs(int n) {
        if(n == 0) {
            return 0;
        }
        return fiboRecursive(n);
    }

    public static int fiboRecursive(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        return fiboRecursive(n - 1) + fiboRecursive(n - 2);
    }

    public int climbStairsDp(int n) {
        if(n == 0) {
            return 0;
        }
        this.table = new Integer[n + 1];
        return fiboDp(n);
    }

    public int fiboDp(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        if (table[n] == null) {
            table[n] = fiboDp(n - 1) + fiboDp(n - 2);
        }
        return table[n];
    }
}
