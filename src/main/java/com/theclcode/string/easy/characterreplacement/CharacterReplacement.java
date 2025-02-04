package com.theclcode.string.easy.characterreplacement;

public class CharacterReplacement {

    public static void main(String[] args) {
        String string = "KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF";
//        int res = characterReplacement(string, 1);
        int res = characterReplacement(string, 4);
        System.out.println(res);

    }

    public static int characterReplacement(String string, int k) {
        int[] map = new int[26];
        int i = 0, maxFrequency = 0, res = 0;
        for(int j = 0; j < string.length(); j++) {
            char c = string.charAt(j);
            map[c-'A']++;
            maxFrequency = Math.max(maxFrequency, map[c-'A']);
            if((j - i + 1) - maxFrequency > k) {
                map[string.charAt(i)-'A']--;
                i++;
            }
            res = Math.max(res, (j - i) + 1);
        }
        return res;
    }

}
