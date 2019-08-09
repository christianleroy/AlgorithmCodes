package com.theclcode.maxcalculator;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int t=1; t<=testCases; t++){
            int numberOfCharacters = sc.nextInt();
            int[] characters = new int[numberOfCharacters];
            boolean[] operators = new boolean[numberOfCharacters];
            String sequence="";
            for(int i=0; i<numberOfCharacters; i++){
                sequence+=""+i;
                int character = sc.nextInt();
                characters[i] = character;
                if(character<0){
                    operators[i] = true;
                }
            }
            String[] permutations = new String[getNumberOfCombinations(numberOfCharacters)];
            Result result = new Result();
            result.setPermutations(permutations);
            permutation(sequence.toCharArray(), 0, numberOfCharacters-1, result);

            int maxResult=0;
            for(String permutation : result.getPermutations()){
                int beginning = Integer.parseInt(permutation.substring(0,1));
                int end = Integer.parseInt(permutation.substring(permutation.length()-1));
                if(operators[beginning] || operators[end]){
                    continue;
                }


            }


        }
        /*
        1. Build a string containing all characters, ie: [1, -2, 3] = 1-23
        2. Build an integer until hyphen is found (-).
        3. If hyphen is found, evaluate which operator it refers to.
        4. Build another integer until meet another hyphen. Repeat step 3.
        5. Evaluate entire operation.
        6. Record highest result yielded.
        */
    }



    static void printCString(char[] str, Result result){
        for (int i = 0; i < str.length && str[i] != 0; i++){
            System.out.print(str[i]);
        }
        result.getPermutations()[result.getIndex()]=new String(str);
        System.out.print("\n");
    }

    static void swap(char[] str, int x, int y){
        char temp = str[x];
        str[x] = str[y];
        str[y] = temp;
    }

    public static void permutation(char[] str, int left, int right, Result result){
        if (left == right){
            printCString(str, result);
            result.setIndex(result.getIndex()+1);
        }
        else{
            for (int i = left; i <= right; i++){
                swap(str, left, i);
                permutation(str, left+1, right, result);
                swap(str, left, i); //backtrack
            }
        }
    }

    public static int getNumberOfCombinations(int length){
        int result=length;
        while(length>1){
            result=result*(length-1);
            length--;
        }
        return result;
    }

    public static class Result{
        private int index;
        private String[] permutations;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String[] getPermutations() {
            return permutations;
        }

        public void setPermutations(String[] permutations) {
            this.permutations = permutations;
        }
    }
}
