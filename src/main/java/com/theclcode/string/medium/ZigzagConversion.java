package com.theclcode.string.medium;

// https://leetcode.com/problems/zigzag-conversion/
public class ZigzagConversion {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 4));
    }

    public static String convert(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        StringBuilder builder = new StringBuilder();

        /*
        A     G
         B   F H
          C E   I
           D     J
        */

        // The distance between the highest point (1st row)
        // to the next character at the highest point. A -> G
        final int depth = (numRows - 1) * 2;

        for (int i = 1; i <= numRows; i++) {
            // Distance between the current character
            // to the next character in the same row. B -> F
            int interval;
            int difference;
            if (i == 1 || i == numRows) {
                interval = depth;
                difference = interval;
            } else {
                interval = (numRows - i) * 2;
                difference = depth - interval;
            }

            int idx = i - 1;

            int temp;
            while (idx < s.length()) {
                builder.append(s.charAt(idx));
                idx += interval;
                temp = interval;
                // Swap the interval with difference.
                // at low point and high point of zigzag. B->F distance is 4, but F -> H distance is 2.
                // Computed as the difference between the current rowNumber and the depth.
                interval = difference;
                difference = temp;
            }
        }

        return builder.toString();

    }
}
