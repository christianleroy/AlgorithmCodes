package com.theclcode.test;
public class Solution {

    public static void main(String[] args){
//        String permutation="671-11-35";
        String permutation="1-11";
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

        if(index-1==-1){
            System.out.print(numbers[0]);
        } else if(index-1==0) {
            System.out.print(evaluate(operators[0], numbers[0], numbers[1]));
        } else if(index-1==1){
            if((operators[0]=="+" || operators[0]=="-") && (operators[1]=="*" || operators[1]=="/")){
                double result = evaluate(operators[1], numbers[1], numbers[2]);
                result = evaluate(operators[0], numbers[0], result);
                System.out.print(result);
            } else {
               double result = evaluate(operators[0], numbers[0], numbers[1]);
               result = evaluate(operators[1], result, numbers[2]);
               System.out.print(result);
            }
        }
    }

    public static double evaluate(String operator, double number1, double number2){
        double result=0;
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

}
