package com.theclcode.smallproblems.reverseinteger;

public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
        System.out.println(reverse(100002));
        System.out.println(reverse(483648));

    }

    public static int reverse(int x) {
        long y;
        long power = (long) Math.log10(Math.abs((long) x));
        long result = 0;
        while (x != 0) {
            y = x % 10;
            result += y * Math.pow(10, power);
            x = x / 10;
            power = (long) Math.log10(Math.abs(x));
            if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return (int) result;
    }

    public static int reverseSimpler(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
