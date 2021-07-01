package com.theclcode.test;

import java.util.Arrays;

public class Sandbox {

    public static void main(String[] args) {
        System.out.println("Test commit");
        isUnique();
    }

    static void isUnique() {
        String[] words = { "abs", "abc", "weird", "words", "worded", "wordiee"};

        for(String word : words) {
            int checker = 0;
            boolean isUnique = true;
            for(int i = 0; i < word.length(); i++) {
                int val = word.charAt(i) - 'a';
                if((checker & 1 << val) > 0) {
                    isUnique = false;
                    break;
                }
                checker |= 1 << val;
            }
            System.out.format("%s is unique? %s %n", word, isUnique);
        }


    }


}
