package com.theclcode.smallproblems.longestpalindrome;

//https://leetcode.com/problems/longest-palindrome/
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "abcdba";
        System.out.println(longestPalindrome(s));
    }

    public static int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray()) {
            count[c]++;
        }

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            // Check if answer is even and if v has leftover.
            // If it is, palindrome can accommodate one unpartnered letter, so add it.
            if (ans % 2 == 0 && v % 2 == 1) {
                ans++;
            }
        }
        return ans;
    }
}
