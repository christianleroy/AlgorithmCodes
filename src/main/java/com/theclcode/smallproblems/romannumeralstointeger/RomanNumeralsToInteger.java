package com.theclcode.smallproblems.romannumeralstointeger;

public class RomanNumeralsToInteger {

    public static void main(String[] args) {
        String ri = "MCMXCIV";
        System.out.println(romanToInt(ri));
    }

    public static int romanToInt(String s) {
        if(s == null || s.isEmpty()){
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < s.length(); ) {
            int val = getValue(s.charAt(i));
            int next = 0;
            if (i + 1 < s.length()) {
                next = getValue(s.charAt(i + 1));
            }
            if (val < next) {
                ans += next - val;
                i += 2;
            } else {
                ans += val;
                i++;
            }
        }
        return ans;
    }

    static int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
