package com.theclcode.array.medium;

import java.util.HashMap;
import java.util.Map;


// https://leetcode.com/problems/count-good-meals/
public class CountGoodMeals {

    public static void main(String[] args) {
        int[][] deliciousness = {
                {1, 1, 3},
                {1, 1, 1, 1, 1, 3, 3},
                {1,1,1,3,3,3,7},
                {1,3,5,7,9}
        };

        for(int[] delicious : deliciousness) {
            System.out.println(countPairs(delicious));
        }

    }

    public static int countPairs(int[] deliciousness) {
        int mod = 1000000007;
        Map<Integer, Integer> table = new HashMap<>();
        long count = 0;

        for(int num : deliciousness) {
            int power = 1;
            for(int i = 0; i < 22; i++) {
                if(table.containsKey(power - num)) {
                    count += table.get(power - num);
                    count %= mod;
                }
                power *= 2;
            }
            table.put(num, table.getOrDefault(num, 0) + 1);
        }

        return (int) count;

    }
}
