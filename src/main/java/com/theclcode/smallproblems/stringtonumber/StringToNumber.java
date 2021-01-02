package com.theclcode.smallproblems.stringtonumber;

public class StringToNumber {


    public static void main(String[] args) {
        String num = "101251235";
        stringToNumberNew(num);
        stringToNumberOld(num);
    }

    public static int stringToNumberNew(String num){
        int res = 0;
        for (int i = 0; i < num.length(); i++) {
            res = res * 10 + (num.charAt(i) - '0');
        }
        System.out.println(res);
        return res;
    }

    public static int stringToNumberOld(String num) {
        int res = 0;
        for (int i = 0, j = num.length() - 1; i < num.length(); i++, j--) {
            int multiplier = (int) Math.pow(10, j);
            res += (num.charAt(i) - '0') * multiplier;
        }
        System.out.println(res);
        return res;
    }
}
