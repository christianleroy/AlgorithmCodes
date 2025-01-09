package com.theclcode.math.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://www.hackerrank.com/challenges/bonetrousle/problem
// 30 points out of 50, imperfect but not timeout. Don't know what's lacking tbh.
public class BoneTrousle {

    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++) {
            System.out.println(bonetrousle(i, 100, 7) + " Target : " + i);
        }
    }

    public static List<Long> bonetrousle(long n, long k, int b) {
        long target = n;
        long availableBoxValue = k;
        long[] customerBoxes = new long[b];

        fillArray(customerBoxes);

        long min = 0, max = 0;
        int i = 1;
        long j = availableBoxValue;
        for(; i <= customerBoxes.length; i++, j--) {
            min += i;
            max += j;
        }

        List<Long> result = new ArrayList<>();
        if(target < min || target > max) {
            result.add(-1L);
            return result;
        }

        long currentSticks = min;

        int end = customerBoxes.length - 1;
        long lastAvailableBox = availableBoxValue;

        while(currentSticks != target) {
            if(currentSticks < target) {
                long diff = target - currentSticks;
                if(customerBoxes[end] + diff >= lastAvailableBox) {
                    currentSticks += lastAvailableBox - customerBoxes[end];
                    customerBoxes[end--] = lastAvailableBox;
                    lastAvailableBox--;
                } else {
                    currentSticks -= customerBoxes[end];
                    long sticks =  customerBoxes[end] + diff;
                    currentSticks += sticks;
                    customerBoxes[end--] = sticks;
                }
            }
        }

        Arrays.stream(customerBoxes).forEach(result::add);
        return result;

    }

    public static void fillArray(long[] array) {
        for(int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }
}
