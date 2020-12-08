package com.theclcode.problems.bonetrousle;


import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.LongStream;

//Wrong answer https://www.hackerrank.com/challenges/bonetrousle/problem
public class BonetrousleMath {

    private long sticksNeeded;
    private long boxesAvailable;
    private int noOfBoxesNeeded;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; i++) {
            BonetrousleMath s = new BonetrousleMath(sc.nextLong(), sc.nextLong(), sc.nextInt());
            System.out.println(Arrays.toString(s.bonetrousle()));
        }
    }

    BonetrousleMath(long sticksNeeded, long boxesAvailable, int noOfBoxesNeeded) {
        this.sticksNeeded = sticksNeeded;
        this.boxesAvailable = boxesAvailable;
        this.noOfBoxesNeeded = noOfBoxesNeeded;
    }


    long[] bonetrousle() {

        if (noOfBoxesNeeded == 1) {
            if (sticksNeeded <= boxesAvailable) {
                return new long[]{sticksNeeded};
            } else {
                return new long[]{-1};
            }
        }

        long min = (noOfBoxesNeeded * (noOfBoxesNeeded + 1)) / 2;
        long max = (noOfBoxesNeeded * (2 * boxesAvailable) - noOfBoxesNeeded + 1) / 2;

        if (sticksNeeded < min || sticksNeeded > max) {
            return new long[]{-1L};
        }

        long res[] = LongStream.iterate(1, e -> e + 1).limit(noOfBoxesNeeded).toArray();
        long r = (sticksNeeded - min) % noOfBoxesNeeded;
        long div = (sticksNeeded - min) / noOfBoxesNeeded;

        for (int i = 0; i < res.length; i++) {
            res[i] += div;
        }

        if (r > 0) {
            int c = noOfBoxesNeeded - 1;
            while (r > 0) {
                res[c] += 1;
                c--;
                r--;
            }
        }
        return res;
    }

}
