package com.theclcode.math.easy;

//https://leetcode.com/problems/add-binary/description/
public class AddBinary {

    public static void main(String[] args) {
        String a = "0101";
        String b = "1101";
        System.out.println(addBinary(a,b));
    }

    public static String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;

        while(i >= 0 || j >=0) {
            if(i >= 0) {
                carry += a.charAt(i--) - '0';
            }
            if(j >= 0) {
                carry += b.charAt(j--) - '0';
            }
            builder.insert(0, carry % 2);
            carry /= 2;
        }

        if(carry > 0) {
            builder.insert(0, carry);
        }

        return builder.toString();
    }
}
