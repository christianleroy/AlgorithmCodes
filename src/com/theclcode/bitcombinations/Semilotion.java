package com.theclcode.bitcombinations;

import java.util.Arrays;
import java.util.Vector;

public class Semilotion {

    public static void init(Vector<String>[][] DP, int k){
        for(int i=0; i<=k; i++){
            for(int j=0; j<=k; j++){
                DP[i][j] = new Vector<>();
            }
        }
    }

    public static void main(String[] args){
        findCombinations(5, 2);
    }

    public static void findCombinations(int k, int ones){
        Vector<String>[][] DP = new Vector[k+1][k+1];
        init(DP, k);
        String prefix = "";

        for (int len=0; len <= k; len++)
        {
            DP[len][0].add(prefix);
            prefix = prefix + "0";
        }

        for (int len = 1; len <= k; len++) {
            for (int n = 1; n <= len; n++) {
                for (String str : DP[len - 1][n]){

                    DP[len][n].add("0" + str);
                }
                for (String str : DP[len - 1][n - 1]) {
                    DP[len][n].add("1" + str);
                }
            }
        }

        for (String str : DP[k][ones]){
            System.out.println(str+" ");
        }
    }
}
