package com.theclcode.smallproblems.longestconsecutive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public static void main(String[] args) {
        int[] arr = {100, 4, 100, 200, 1, 3, 2};
        System.out.println(longestConsecutive(arr));

    }

    public static int longestConsecutive(int[] nums) {

        Set<Integer> set = Arrays.stream(nums).collect(HashSet::new, Set::add, Set::addAll);
        int max = 0;
        for (int i : nums) {
            if (set.contains(i - 1)) {
                continue;
            }
            int curr = 1;
            while (set.contains(i + 1)) {
                i += 1;
                curr++;
            }
            if (curr > max) {
                max = curr;
            }
        }
        return max;

    }
}
