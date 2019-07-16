package com.theclcode.bitcombinations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int noOfTestCases = new Integer(reader.readLine());
        List<int[]> testCases = new ArrayList<>();
        while(noOfTestCases>0){
            String testCaseSpec = reader.readLine();
            int[] zeroesAndOnes = {
                    Integer.parseInt(testCaseSpec.substring(0,testCaseSpec.indexOf(" "))),
                    Integer.parseInt(testCaseSpec.substring(testCaseSpec.indexOf(" ")+1))
            };
            testCases.add(zeroesAndOnes);
            noOfTestCases--;
        }
        for(int i=0; i<testCases.size(); i++){
            System.out.println("Case #"+(i+1));
            StringBuilder bits = getCombinations(testCases.get(i));
            System.out.println(bits);
            move(0, bits, testCases.get(i),"");
        }
    }

    public static StringBuilder getCombinations(int[] zeroesAndOnes) {
        String zeroes = new String(new char[zeroesAndOnes[0]-zeroesAndOnes[1]]).replace("\0", "0");
        String ones = new String(new char[zeroesAndOnes[1]]).replace("\0", "1");
        StringBuilder bits = new StringBuilder(zeroes+ones);
        return bits;
    }

    public static void move(int index, StringBuilder bits, int[] zeroesAndOnes, String prefix) {
        if(zeroesAndOnes[1]==0){ return; }
        if (index < bits.length()) {
            if (bits.charAt(index) == '1' && index>0 && bits.charAt(index-1)=='0') {
                bits.setCharAt(index - 1, '1');
                bits.setCharAt(index, '0');
                System.out.println(prefix+bits);
            }
            move(index+1, bits, zeroesAndOnes, prefix);
        } else {
            if(zeroesAndOnes[1]>0){
                if(bits.length()>2 && bits.charAt(1)!='1'){
                    move(0, bits, zeroesAndOnes, prefix);
                } else {
                    zeroesAndOnes[0]-=1;
                    zeroesAndOnes[1]-=1;
                    StringBuilder _bits = getCombinations(zeroesAndOnes);
                    prefix+="1";
                    System.out.println(prefix+_bits);
                    move(0, _bits, zeroesAndOnes, prefix);
                }
            }
        }
    }
}
