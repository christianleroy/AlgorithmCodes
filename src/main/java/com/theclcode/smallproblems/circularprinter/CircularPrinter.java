package com.theclcode.smallproblems.circularprinter;

public class CircularPrinter {

    // Compute the least number of time a circular printer would take to print a word.
    // ABCD - would take 4 seconds, A moving to B, B to C, C to D. All right moves. Total 3 seconds.
    // AZGB - would take 13 seconds. A to Z is 1 (will turn left), Z to G would be 7 (right movement), G to B (5, left)

    public static void main(String[] args) {
        String[] words = {"AZGB", "ABCD"};
        for(String word : words) {
            System.out.println(getTime(word));
        }
    }

    public static long getTime(String s) {
        if(s.charAt(0) != 'A') {
            s = "A" + s;
        }
        int currPos = 0;
        int time = 0;
        for(int i = 0; i < s.length(); i++) {
            int left = (s.charAt(currPos) - 'A') + 1;
            int right = (s.charAt(i) - 'A') + 1;
            int path = Math.abs(left - right);
            if(path > 13) {
                path = Math.abs(path - 26);
            }
            time += path;
            currPos = i;
        }
        return time;
    }
}
