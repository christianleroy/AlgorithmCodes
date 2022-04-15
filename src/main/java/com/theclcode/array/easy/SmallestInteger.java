package com.theclcode.array.easy;

import java.util.HashSet;
import java.util.Set;

// Smallest positive integer not in given array
public class SmallestInteger {

    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 1, 4, 1, 5, 7};
        System.out.println(solution(nums));
    }
    public static int solution(int[] A) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < A.length; i++) {
            if(A[i] > 0) {
                set.add(A[i]);
            }
        }
        int smallest = 1;
        while(set.contains(smallest)) {
            smallest++;
        }
        return smallest;
    }
}
