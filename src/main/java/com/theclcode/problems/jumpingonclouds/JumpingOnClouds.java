package com.theclcode.problems.jumpingonclouds;

//https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
public class JumpingOnClouds {

    public static void main(String[] args) {
        int[][] arr = {{0, 1, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 1, 0},{0,0,0,1,0,0}};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(jumpingOnClouds(arr[i]));
        }
    }

    static int jumpingOnClouds(int[] c) {
        int steps = 0;
        int i = 2;
        while (i <= c.length) {
            steps++;
            if(i == c.length){
                break;
            }
            if (c[i] == 1) {
                i--;
            }
            i += 2;
        }
        return steps;
    }
}
