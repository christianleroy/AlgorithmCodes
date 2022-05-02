package com.theclcode.searching.binarysearch;

//https://leetcode.com/problems/first-bad-version/
public class FirstBadVersion {
    static int firstMin = 15;

    public static void main(String[] args) {
        System.out.println(firstBadVersion(30));
    }

    public static int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int middle = start + (end - start) / 2;
            if (isBadVersion(middle)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return start;
    }

    public static boolean isBadVersion(int n) {
        if (n >= firstMin) {
            return true;
        }
        return false;
    }
}
