package com.theclcode.smallproblems;

public class StringPalindromeChecker {

    public static void main(String[] args) {

        String[] palindromes = {"abba", "abbba", "abcd", "absba",
                "abssba", "abca", "abxaa", "a",
                "ab", "aa", "Race car", "Yo banana boy"};


        for (String palindrome : palindromes) {
            isPalindrome(palindrome);
        }
    }

    private static void isPalindrome(String palindrome) {
        boolean isPalindrome = true;
        palindrome = palindrome.trim().toLowerCase().replaceAll("\\s+", "");
        for (int i = 0, j = palindrome.length() - 1; i < (palindrome.length() / 2); i++, j--) {
            if (palindrome.charAt(i) != palindrome.charAt(j)) {
                isPalindrome = false;
            }
        }
        System.out.format("%s is palindrome: %s%n", palindrome, isPalindrome);
    }

}
