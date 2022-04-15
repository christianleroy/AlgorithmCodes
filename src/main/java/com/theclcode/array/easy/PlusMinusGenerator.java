package com.theclcode.array.easy;

public class PlusMinusGenerator {

    public static void main(String[] args) {
        for(int i = 1; i <= 5; i++) {
            System.out.println(solution(i));
        }
    }


    public static String solution(int N) {
        if (N <= 0) {
            return "";
        }
        int M = N / 2;
        StringBuilder result = new StringBuilder();
        while (M > 0) {
            result.append("+-");
            M--;
        }
        ;

        if (N % 2 != 0) {
            result.append("+");
        }
        return result.toString();
    }
}
