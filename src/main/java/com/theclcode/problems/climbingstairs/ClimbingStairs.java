package com.theclcode.problems.climbingstairs;

public class ClimbingStairs {
    private Integer[] table;

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(5));
        System.out.println(new ClimbingStairs().climbStairs(2));
        System.out.println(new ClimbingStairs().climbStairs(3));
    }

    public int climbStairs(int n){
        this.table = new Integer[n+1];
        return fib(n);
    }

    public int fib(int n){
        if(n < 0){
            return 0;
        } else if(n == 0){
            return 1;
        }
        if(table[n] == null) {
            table[n] = fib(n - 1) + fib(n - 2);
        }
        return table[n];
    }
}
