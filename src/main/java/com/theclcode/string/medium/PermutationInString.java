package com.theclcode.string.medium;

//https://leetcode.com/problems/permutation-in-string/
public class PermutationInString {
    
    public static void main(String[] args) {
        String s1 = "xa";
        String s2 = "ayx";
        System.out.println(checkInclusion(s1, s2));
    }

    public static boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        int[] table = new int[26];
        int[] verify = new int[26];
        int count = 0;

        // based on s1 length, so this becomes the initial window. window length == s1 length
        for (int i = 0; i < s1.length(); i++) {
            table[s1.charAt(i) - 'a']++;
            verify[s2.charAt(i) - 'a']++;
        }

        // matches the number of characters s1 and s2 have in common in the initial window
        // if count reaches 26, then it means the check already passed on the initial window.
        for (int i = 0; i < 26; i++) {
            if (table[i] == verify[i]) {
                count++;
            }
        }

        // Sliding the window. Window length == s1 length
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (count == 26) {
                return true;
            }

            int left = s2.charAt(i) - 'a'; // Window moving leftwards
            int right = s2.charAt(i + s1.length()) - 'a'; // Window right end

            verify[right]++; //Include right end of window to count

            if (verify[right] == table[right]) {
                count++;
            } else if (verify[right] == table[right] + 1) {
                count--;
            }

            verify[left]--; // Remove previous left end from count

            if (verify[left] == table[left]) {
                count++;
            } else if (verify[left] == table[left] - 1) {
                count--;
            }
        }
        return count == 26;
    }

}
