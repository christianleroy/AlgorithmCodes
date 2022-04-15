package com.theclcode.string.easy;

public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
    }

    public static int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;

        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        for (; i >= 0 && s.charAt(i) != ' '; i--) {
            length++;
        }

        return length;
    }
}
