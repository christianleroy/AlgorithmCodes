package com.theclcode.cracking.iq1;

public class InterviewQuestions1 {


    public static void main(String[] args) {
//        isUnique();
//        checkPermutation();
        isPermutationPalindrome();
    }

    //Check if string has all unique cahracters
    public static void isUnique() {
        String[] words = {"abc", "abba", "abcd", "dfefg", "azdhq"};
        for(String word: words) {
            boolean[] characters = new boolean[26];
            boolean isUnique = true;
            for(int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if(characters[idx]) {
                    isUnique = false;
                }
                characters[idx] = true;
            }
            System.out.format("%s: %s %n", word, isUnique);
        }
    }

    //Check if wordB is a permutation of wordA
    public static void checkPermutation() {
        boolean[] characters = new boolean[26];
        boolean isPermutation = true;

        String wordA = "abcd";
        String wordB = "adbc";

        if(wordA.length() != wordB.length()) {
            isPermutation = false;
        }

        for(int i = 0; i < wordA.length(); i++) {
            int idx = wordA.charAt(i) - 'a';
            characters[idx] = true;
        }

        for(int j = 0; j < wordB.length(); j++) {
            int idx = wordB.charAt(j) - 'a';
            if(!characters[idx]) {
                isPermutation = false;
            }
        }
        System.out.format("%s is permutation of %s: %s", wordB, wordA, isPermutation);

    }


    //Check if word is a permutation of a palindrome
    public static void isPermutationPalindrome() {
        String[] words = { "abbaz", "azza", "aoctact", "abba azza", "acto", "aboa", "abbaba", "aba aba"};
        for(String word: words) {
            int[] count = new int[26];
            for(int i =0; i < word.length(); i++){
                if(' ' == word.charAt(i)){
                    continue;
                }
                int idx = word.charAt(i) - 'a';
                count[idx]++;
            }
            boolean isPalindrome = true;
            int oddCount = 0;
            for(int i : count) {
                if(i % 2 != 0) {
                    oddCount++;
                }
                if(oddCount > 1) {
                    isPalindrome = false;
                    break;
                }
            }
            System.out.format("%s is permutation of a palindrome: %s %n", word, isPalindrome);
        }
    }
}
