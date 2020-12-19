package com.theclcode.smallproblems.stringtonumber;

public class StringToNumber {
    public static void main(String[] args) {
        String a = "101251235";
        int res = 0;

        for (int i = 0, j = a.length() - 1; i < a.length(); i++, j--) {
            int multiplier = (int) Math.pow(10, j);
            res += (a.charAt(i) - '0') * multiplier;
        }

        System.out.println(res);
    }
}
