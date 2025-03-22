package com.theclcode.string.medium;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/optimal-partition-of-string/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency
public class OptimalPartitionOfAString {

    public static void main(String[] args) {
        System.out.println(partitionString("abacaba"));
    }

    public static int partitionString(String s) {

        Set<Character> set = new HashSet<>();
        int count = 1;
        for(int i = 0; i < s.length(); i++) {
            if(set.contains(s.charAt(i))) {
                System.out.println(s + "\n");
                set.clear();
                set.add(s.charAt(i));
                count++;
            } else {
                set.add(s.charAt(i));
            }
        }

        return count;
    }

}
