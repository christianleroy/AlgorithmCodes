package com.theclcode.swc.newsletter.nov19.numbers;

import java.util.Scanner;

public class Numbers {

    public void run(){
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        int[][] numbers;
        int c;
        int d;

        for(int i=1; i<=testCases; i++){
            int inputSize = sc.nextInt();
            numbers = new int[inputSize][];
            for(int j=0; j<inputSize; j++){
                numbers[j] = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt() };
            }
            int res = check(numbers);
            System.out.println("Case #"+i+": "+res);

        }
    }

    public int check(int[][] numbers){
        int validNumbers = 0;
        for(int i=10000; i<=99999; i++){
            String a = String.valueOf(i);
            boolean isValid = true;
            for(int j=0; j<numbers.length; j++){
                String b = String.valueOf(numbers[j][0]);
                int[] freqA = new int[10];
                int[] freqB = new int[10];
                int c=0, d=0;
                for(int k=0; k<5; k++){
                    int _a = Integer.valueOf(String.valueOf(a.charAt(k)));
                    int _b = Integer.valueOf(String.valueOf(b.charAt(k)));
                    if(_a == _b){
                        c++;
                    } else {
                        freqA[_a]++;
                        freqB[_b]++;
                    }
                }
                for(int l=0; l<10; l++){
                    d += Math.min(freqA[l], freqB[l]);
                }
                if(c != numbers[j][1] || d!=numbers[j][2]){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                validNumbers++;
            }
        }
        return validNumbers;
    }

    public static void main(String[] args) {
        new Numbers().run();
    }
}
