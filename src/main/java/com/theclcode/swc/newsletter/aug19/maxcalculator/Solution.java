package com.theclcode.swc.newsletter.aug19.maxcalculator;

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
                StringBuilder expression = new StringBuilder();
                for(int i=0; i<permutation.length(); i++){
                    int index = Integer.parseInt(permutation.substring(i,i+1));
                    expression.append(characters[index]);
                }

                int evaluation = evaluateString(expression.toString());
                if(evaluation>maxResult){
                    maxResult = evaluation;
                }
            }
            System.out.println("Case #"+t+": "+maxResult);
        }

    }

    public static int evaluateString(String permutation){
        boolean isOperator=false;
        boolean isPrevOperator=false;
        String operation = "";
        String[] operators = new String[2];
        int index=0;
        for(int i=0; i<permutation.length(); i++){
            if(isOperator){
                char op = permutation.charAt(i);
                switch(op){
                    case '1':
                        operators[index]="+";
                        break;
                    case '2':
                        operators[index]="-";
                        break;
                    case '3':
                        operators[index]="*";
                        break;
                    case '4':
                        operators[index]="/";
                        break;
                }
                index++;
                isOperator=false;
                isPrevOperator=true;
                operation+="_";
                continue;
            }

            if(permutation.charAt(i)!='-'){
                operation+=permutation.charAt(i);
                isPrevOperator=false;
            } else{
                if(isPrevOperator){
                    return 0;
                }
                isOperator=true;
            }
        }
        String[] str = operation.split("_");
        int[] numbers = new int[3];
        for(int counter=0; counter<str.length; counter++){
            numbers[counter] = Integer.parseInt(str[counter]);
        }

        int result = 0;
        if(index-1==-1){
           result = numbers[0];
        } else if(index-1==0) {
           result = evaluateExpression(operators[0], numbers[0], numbers[1]);
        } else if(index-1==1){
            if((operators[0]=="+" || operators[0]=="-") && (operators[1]=="*" || operators[1]=="/")){
                result = evaluateExpression(operators[1], numbers[1], numbers[2]);
                result = evaluateExpression(operators[0], numbers[0], result);
            } else {
                result = evaluateExpression(operators[0], numbers[0], numbers[1]);
                result = evaluateExpression(operators[1], result, numbers[2]);
            }
        }
        return result;
    }

    public static int evaluateExpression(String operator, int number1, int number2){
        int result=0;
        if(operator.equals("+")){
            result=number1+number2;
        } else if(operator.equals("-")){
            result=number1-number2;
        } else if(operator.equals("*")){
            result=number1*number2;

        } else if(operator.equals("/")){
            result=number1/number2;
        }
        return result;
    }

    static void printCString(char[] str, Result result){
        result.getPermutations()[result.getIndex()]=new String(str);
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
