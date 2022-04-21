package com.theclcode.string.easy;

// https://leetcode.com/problems/reverse-words-in-a-string-iii/
public class ReverseWordsInString {

    public static void main(String[] args) {
        String s = "Let's abc";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        char[] result = new char[s.length()];
        int i = 0, j = 0;
        for (; j < s.length(); j++) {
            if (s.charAt(j) == ' ') {
                copy(i, j, result, s);
                result[j] = s.charAt(j);
                i = j + 1;
            }
        }
        copy(i, j, result, s);
        return new String(result);
    }

    private static void copy(int i, int j, char[] result, String s) {
        for (int a = i, b = j - 1; a < j; a++, b--) {
            result[a] = s.charAt(b);
        }
    }
}
