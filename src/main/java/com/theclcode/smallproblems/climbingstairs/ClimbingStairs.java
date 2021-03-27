package com.theclcode.smallproblems.climbingstairs;

//fibonacci
public class ClimbingStairs {
    private Integer[] table;

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(5));
        System.out.println(new ClimbingStairs().climbStairs(2));
        System.out.println(new ClimbingStairs().climbStairs(0));
        System.out.println(new ClimbingStairs().climbStairs(1));
    }

    public int climbStairs(int n) {
        if(n == 0) {
            return 0;
        }
        this.table = new Integer[n + 1];
        return fiboRecursive(n);
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

    public static int fiboRecursive(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        return fiboRecursive(n - 1) + fiboRecursive(n - 2);
    }
}
