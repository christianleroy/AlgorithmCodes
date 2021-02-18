package com.theclcode.smallproblems.countconsistentstrings;

public class CountConsistentStrings {

    public static void main(String[] args) {
        String allowed = "abc";
        String[] words = {"a", "b", "c", "ab", "abc", "acd"};
        System.out.println(countConsistentStrings(allowed, words));
    }

    public static int countConsistentStrings(String allowed, String[] words) {
        if((allowed == null || allowed.isEmpty()) || (words == null || words.length == 0)){
            return 0;
        }

        boolean[] ok = new boolean[26];
        int valid = 0;

        for(int i=0; i < allowed.length(); i++){
            ok[allowed.charAt(i) - 'a'] = true;
        }

        for(String word : words){
            boolean isAllowed = true;
            for(int i=0; i < word.length(); i++){
                if(!ok[word.charAt(i) - 'a']){
                    isAllowed = false;
                    break;
                }
            }
            if(isAllowed){
                valid++;
            }
        }
        return valid;
    }
}
