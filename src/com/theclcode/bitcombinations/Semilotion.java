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

       int k = 5;
       Vector<String>[][] DP = new Vector[k+1][k+1];
       init(DP, k);
       String prefix = "";

        for (int len=0; len <= k; len++)
        {
            DP[len][0].add(prefix);
            prefix = prefix + "0";
        }

        for (int len = 1; len <= k; len++)
        {
            for (int n = 1; n <= len; n++)
            {
                // prefix 0 to all combinations of length len-1
                // with n ones
                for (String str : DP[len - 1][n])
                    DP[len][n].add("0" + str);

                // prefix 1 to all combinations of length len-1
                // with n-1 ones
                for (String str : DP[len - 1][n - 1])
                    DP[len][n].add("1" + str);
            }
        }

        System.out.println(DP[k]);

//        for (int n = 1; n <= k; n++)
//        {
//            for (String str : DP[k][n]){
//                System.out.print(str+" ");
//            }
//            System.out.println("");
//        }

    }
}
