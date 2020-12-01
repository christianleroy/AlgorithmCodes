package com.theclcode.problems.climbingtheleaderboard;

import java.util.*;

//binary search tree, set
//https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
public class ClimbingTheLeaderboard {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int leaders = sc.nextInt();

        //The input is already ordered, can improve to use a stack or list instead.
        Set<Integer> leaderSet = new TreeSet<>(Comparator.reverseOrder());

        for(int i = 0; i <leaders; i++){
            leaderSet.add(sc.nextInt());
        }

        Integer[] leaderboard = leaderSet.toArray(new Integer[]{});

        int[] scores = new int[sc.nextInt()];
        for(int i=0; i<scores.length; i++){
            scores[i] = sc.nextInt();
        }

        for(int i : getRankings(leaderboard, scores)){
            System.out.println(i);
        }

    }

    public static int[] getRankings(Integer[] leaderboard, int[] scores){
        Map<Integer, Integer> rankings = new HashMap<>();

        int[] result = new int[scores.length];
        for(int i=0; i < scores.length; i++){
            if(rankings.containsKey(scores[i])){
                result[i] = rankings.get(scores[i]);
            } else {
                int rank = bst(leaderboard, 0, leaderboard.length-1, scores[i]);
                result[i] = rank;
                rankings.put(scores[i], rank);
            }
        }
        return result;

    }

    public static int bst(Integer[] arr, int start, int end, int item){
        if(start == end){
            if(item == arr[start]){
                return ++start;
            } else {
                int rank = item > arr[start] ? start : start + 1;
                rank = rank < 0 ? 0 : rank;
                return rank+1;
            }
        } else {
            int middle = (start + end) / 2;
            if(arr[middle] == item){
                return ++middle;
            } else if(item > arr[middle]){
                return bst(arr, start, middle, item);
            } else {
                return bst(arr, middle+1, end, item);
            }
        }
    }
}
