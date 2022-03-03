package com.theclcode.smallproblems.pairwithsum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PairWithSum {

    public static void main(String[] args) {

        int[] numbers = {1, 2, 9, 7, 6, 22, 11, 7, 9, 10};
        int toFind = 9;

        sorted(numbers, toFind);
        unsorted(numbers, toFind);
    }

    private static void unsorted(int[] numbers, int toFind){
        Set<Integer> tracker = new HashSet<>();
        boolean found = false;
        for(int i : numbers) {
            int key = toFind - i;
            if(tracker.contains(i)) {
                System.out.format("%s, %s %n", i, key);
                found = true;
                break;
            }
            tracker.add(key);
        }
        System.out.println(found ? "Found!" : "Not Found");

    }

    private static void sorted(int[] numbers, int toFind) {
        int[] copy = Arrays.stream(numbers).sorted().toArray();
        System.out.println(Arrays.toString(copy));
        for(int i=0, y=copy.length-1; i<copy.length;){
            if(i == y){
                break;
            }
            if(copy[i] + copy[y] > toFind){
                y--;
            } else if(copy[i] + copy[y] < toFind){
                i++;
            } else {
                System.out.println(copy[i] + ", " + copy[y]);
                System.out.println("Found!");
                return;
            }
        }
        System.out.println("Not Found");
    }

}
