package com.theclcode.string.easy.addstrings;

public class AddStrings {

    public static void main(String[] args) {
        System.out.println(new AddStrings().addStrings("121", "111"));
        System.out.println(new AddStrings().addStrings("9", "111"));
        System.out.println(new AddStrings().addStrings("9234", "111"));
        System.out.println(new AddStrings().addStrings("9333852702227987",
                "85731737104263"));
        System.out.println(new AddStrings().addStrings("204234",
                "0"));
    }


    public String addStrings(String str1, String str2) {

        int i = str1.length() - 1;
        int j = str2.length() - 1;
        int carry = 0;
        int sum;

        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int a = i >= 0 ? str1.charAt(i) - '0' : 0;
            int b = j >= 0 ? str2.charAt(j) - '0' : 0;
            sum = a + b + carry;
            carry = sum / 10;
            stringBuilder.insert(0, (char) ((sum % 10) + '0'));
            i--;
            j--;
        }

        if (carry > 0) {
            stringBuilder.insert(0, (char) (carry + '0'));
        }

        return stringBuilder.toString();

    }
}
