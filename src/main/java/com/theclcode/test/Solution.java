package com.theclcode.test;
public class Solution {

    public static void main(String[] args){
        String permutation="671-11-35";
        boolean isOperator=false;
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
                operation+="_";
                continue;
            }

            if(permutation.charAt(i)!='-'){
                operation+=permutation.charAt(i);
            } else{
                isOperator=true;
            }
        }
        String[] str = operation.split("_");
        int[] numbers = new int[3];
        for(int counter=0; counter<str.length; counter++){
            numbers[counter] = Integer.parseInt(str[counter]);
        }
    }

}
