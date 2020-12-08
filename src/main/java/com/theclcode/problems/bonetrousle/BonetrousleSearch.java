package com.theclcode.problems.bonetrousle;

import com.theclcode.test.Sandbox;

import java.util.Arrays;
import java.util.Scanner;

//Timeout https://www.hackerrank.com/challenges/bonetrousle/problem
public class BonetrousleSearch {
    private long sticks;
    private long boxes;
    private int trips;
    private long[] ans;
    private int marker;
    private boolean found = false;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; i++) {
            BonetrousleSearch s = new BonetrousleSearch(sc.nextInt(), sc.nextInt(), sc.nextInt());
            System.out.println(Arrays.toString(s.bonetrousle()));
        }
    }

    BonetrousleSearch(long sticks, long boxes, int trips) {
        this.boxes = boxes;
        this.sticks = sticks;
        this.trips = trips;
        this.ans = new long[this.trips];
    }


    long[] bonetrousle() {
        this.find(Math.min(boxes, sticks));
        if (found) {
            return ans;
        } else {
            return new long[]{-1};
        }
    }

    public void find(long start) {
        for (long i = start; i > 0 && sticks > 0 && trips > 0 && !found; i--) {
            pick(i);
            if (sticks < i) {
                find(sticks);
            } else {
                find(i - 1);
            }
            unpick(i);
        }
    }

    public void pick(long box) {
        sticks -= box;
        trips -= 1;
        ans[marker++] = box;
        if (sticks == 0 && trips == 0) {
            found = true;
        }
    }

    public void unpick(long box) {
        if (!found) {
            sticks += box;
            trips += 1;
            ans[--marker] = 0;
        }
    }

}
