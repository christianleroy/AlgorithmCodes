package com.theclcode.problems.permutation;

import java.util.Scanner;

//Not my code
class OriginalSolution
{
    static final int MAX_STRING_LENGTH = 10;
    static int stackTop = 0;
    static char[] combinationStack = new char[MAX_STRING_LENGTH];

    static void printCString(char[] str){
        for (int i = 0; i < str.length && str[i] != 0; i++)
        {
            System.out.print(str[i]);
        }
        System.out.print("\n");
    }

    static void swap(char[] str, int x, int y){
        char temp = str[x];
        str[x] = str[y];
        str[y] = temp;
    }

    static void permutation(char[] str, int left, int right){
        if (left == right){
            printCString(str);
        }
        else {
            for (int startAt = left; startAt <= right; startAt++)
            {
                swap(str, left, startAt);
                permutation(str, left+1, right);
                swap(str, left, startAt); //backtrack
            }
        }
    }

    static void push(char ch){
        combinationStack[stackTop++] = ch;
    }

    static void pop(){
        combinationStack[--stackTop] = '\0';
    }

    static void combination(char[] str, int length, int offset, int k){

        if (k == 0){
            printCString(combinationStack);
            return;
        }

        for (int i = offset; i <= length - k; ++i){
            push(str[i]);
            combination(str, length, i+1, k-1);
            pop();
        }
    }


    public static void main(String args[]) throws Exception{
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++){
            String input = sc.next();
            int N = sc.nextInt();
            int K = sc.nextInt();
            char[] str = input.toCharArray();

            System.out.printf("#%d\n", test_case);
//            permutation(str, 0, N-1);
            combination(str, N, 0, K);
        }
        sc.close();
    }
}
