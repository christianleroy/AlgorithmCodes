package com.theclcode.test;

public class Solution {
    static long[] powers = new long[12];
    static final int BASE = 12;
    static {
        for(int i=0; i<=11; i++){
            if(i==0){
                powers[0]=1;
            } else if(i==1){
                powers[i] = BASE;
            } else {
                powers[i] = powers[i-1]*BASE;
            }
        }
    }

    public static void main(String[] args) {
        String test = "test";
        long start = System.nanoTime();
        long result = convertToRadix("ABC");
        System.out.println(result);
        result = convertToRadix("BCA");
        System.out.println(result);
        long elapsedTime = System.nanoTime() - start;
        System.out.println(elapsedTime);
    }

    public static long convertToRadix(String word){
        int length = word.length()-1;
        int hash=0;
        for(int x=0, y=length; x<=length; x++, y--){
            int b = word.charAt(x);
            hash+=word.charAt(x)*powers[y];
        }
        return hash;
    }
}
