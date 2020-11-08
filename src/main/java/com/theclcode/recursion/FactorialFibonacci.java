package com.theclcode.recursion;

public class FactorialFibonacci {

    public static void main(String[] args) {
        for (int i = 0; i <= 29; i++) {
            System.out.format("%s : %s, %s\n", i, fibLong(i), fib(i));
        }
    }

    static int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    static int fibLong(int n) {
        if(n <= 1){
            return n;
        }
        return fibLong(0, 1, 2, n);
    }

    static int fibLong(int x, int y, int curr, int end) {
        if (curr == end) {
            return x + y;
        }
        return fibLong(y, x + y, ++curr, end);
    }

    static int fac(int n) {
        if (n == 0) {
            return 1;
        }
        return n * fac(n - 1);
    }

    static int facIterative(int n) {
        if (n == 0) {
            return 1;
        }
        int result = 1;
        for (int i = n; i > 0; i--) {
            result = result * i;
        }
        return result;
    }
}
