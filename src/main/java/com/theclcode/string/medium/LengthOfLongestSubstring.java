package com.theclcode.string.medium;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String a = "abcdacb";
        System.out.println(lengthOfLongestSubstring(a));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        // last is the last start index of a non-unique character
        // example: abcdacb, by the second b in the end of string, last is start index of c, which is 2, end index of c is 5)
        // this will take into account any repeated characters within the first b and the second b. it will take last index instead of b's start index
        int curr = 0, max = 0, last = 0;

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                int val = map.get(s.charAt(i));
                curr = i - Math.max(val, last);
                map.put(s.charAt(i), i);
                last = Math.max(val, last);
            } else {
                map.put(s.charAt(i), i);
            }

            if (curr > max) {
                max = curr;
            }
        }
        return max;
    }
}
