package com.theclcode.string.easy.atoi;

public class Atoi {

    public static void main(String[] args) {
        String[] strings = {
                "12412412312412341231231", //max int
                "-12312249851123123", //min int
                "+-12", //0
                "+1", //1
                "   +69", //69
                "16      ", //16
                "   8484 a fat dog", //8484
                "00000-14124", //0
                "000004a123", //4
                "123123-123", //123123
                "1 123", //1
                " ", //0
                "   ", //0
                "2147483648", //max int
                "2147483646", //2147483646
                "-2147483647", //-2147483647
                "-2147483646", //-2147483646
                "-2147483649", // -2147483648,
                "-5-" //5

        };

        for(String str : strings) {
            System.out.println(myAtoi(str));
        }
    }

    public static int myAtoi(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        int result = 0;
        int sign = 1;
        boolean signed = false;
        boolean isNumber = false;
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        for (; i < s.length(); i++) {
            if(!isNumber && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
                if(signed) {
                    return 0;
                }
                signed = true;
                if(s.charAt(i) == '-') {
                    sign *= -1;
                }
            } else {
                int c = s.charAt(i) - '0';
                if(c < 0 || c > 26) {
                    break;
                }
                isNumber = true;
                if((result * sign) > Integer.MAX_VALUE / 10 || ((result * sign) == Integer.MAX_VALUE / 10 && c > 7)) {
                    return Integer.MAX_VALUE;
                } else if((result * sign) < Integer.MIN_VALUE / 10 || ((result * sign) == Integer.MIN_VALUE / 10&& c > 8)) {
                    return Integer.MIN_VALUE;
                }
                result = result * 10 + c;
            }
        }
        return result * sign;

    }
}
