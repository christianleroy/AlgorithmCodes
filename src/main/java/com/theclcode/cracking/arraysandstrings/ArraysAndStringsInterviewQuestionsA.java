package com.theclcode.cracking.arraysandstrings;

public class ArraysAndStringsInterviewQuestionsA {

    public static void main(String[] args) {
//        isUnique();
        checkPermutation();
//        isPermutationPalindrome();
//        checkPermutationBig();
    }

    //

    /**
     * Check if string characters are all unique
     * Time: O(N)
     * Space: O(1)
     */
    public static void isUnique() {
        String[] words = {"abc", "abba", "abcd", "dfefg", "azdhq"};
        for (String word : words) {
            boolean[] characters = new boolean[26];
            boolean isUnique = true;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (characters[idx]) {
                    isUnique = false;
                    break;
                }
                characters[idx] = true;
            }
            System.out.format("%s: %s %n", word, isUnique);
        }

    }
    /**
     * Check if string characters are all unique
     * Uses bit shifting instead of a fixed boolean array
     * Time: O(N)
     * Space: O(1)
     */
    public static void isUniqueBit() {
        String[] words = {"abs", "abc", "weird", "words", "worded", "wordiee"};
        for (String word : words) {
            int checker = 0;
            boolean isUnique = true;
            for (int i = 0; i < word.length(); i++) {
                int val = word.charAt(i) - 'a';
                if ((checker & 1 << val) > 0) {
                    isUnique = false;
                    break;
                }
                checker |= 1 << val;
            }
            System.out.format("%s is unique? %s %n", word, isUnique);
        }
    }

    /**
     * Check if wordB is a permutation of wordA
     * Supports only small case unique alphabet strings
     * Time: O(N) where N is the String length of wordA or wordB
     * SpacE: O(1)
     */
    public static void checkPermutation() {
        int[] wordACharacters = new int[26];
        int[] wordBCharacters = new int[26];
        boolean isPermutation = true;

        String wordA = "abcd";
        String wordB = "dbcc";

        if (wordA.length() != wordB.length()) {
            isPermutation = false;
        }

        for (int i = 0; i < wordA.length(); i++) {
            int wordAIdx = wordA.charAt(i) - 'a';
            int wordBIdx = wordB.charAt(i) - 'a';
            wordACharacters[wordAIdx]++;
            wordBCharacters[wordBIdx]++;
        }

        for(int i = 0; i < wordA.length(); i++) {
            int idx = wordA.charAt(i) - 'a';
            if(wordACharacters[idx] != wordBCharacters[idx]) {
                isPermutation = false;
                break;
            }
        }

        System.out.format("%s is permutation of %s: %s", wordB, wordA, isPermutation);

    }

    /**
     * Check if wordB is a permutation of wordA
     * Supports ASCII, non-unique character strings
     * Time: O(N) where N is the String length of wordA or wordB
     * SpacE: O(1)
     */
    public static void checkPermutationBig() {
        String[][] words = {{"abcd", "adbc"}, {"actocat", "tacocat"}, {"abba", "azza"}};
        for(String[] pair: words) {

            String wordA = pair[0];
            String wordB = pair[1];

            int[] checker = new int[128];
            boolean isPermutation = true;
            for(char c : wordA.toCharArray()) {
                checker[c]++;
            }

            for(char c : wordB.toCharArray()) {
                checker[c]--;
                if(checker[c] < 0) {
                    isPermutation = false;
                    break;
                }
            }

            System.out.format("%s is permutation of %s? %s%n", wordB, wordA, isPermutation);
        }
    }

    /**
     * Check if word is a permutation of a palindrome
     * Supports small case alphabet strings only
     * Time: O(N)
     * Space: O(1)
     */
    public static void isPermutationPalindrome() {
        String[] words = {"abbaz", "azza", "aoctact", "abba azza", "acto", "aboa", "abbaba", "aba aba"};
        for (String word : words) {
            int[] count = new int[26];
            for (int i = 0; i < word.length(); i++) {
                if (' ' == word.charAt(i)) {
                    continue;
                }
                int idx = word.charAt(i) - 'a';
                count[idx]++;
            }
            boolean isPalindrome = true;
            int oddCount = 0;
            for (int i : count) {
                if (i % 2 != 0) {
                    oddCount++;
                }
                if (oddCount > 1) {
                    isPalindrome = false;
                    break;
                }
            }
            System.out.format("%s is permutation of a palindrome: %s %n", word, isPalindrome);
        }
    }
}
