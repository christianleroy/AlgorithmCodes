package com.theclcode.smallproblems.pairwithsum;

public class PairWithSum {

    public static void main(String[] args) {
        sorted();
    }

    private static void unsorted(){

    }

    private static void sorted() {
        int[] a = {1, 2, 3, 5};
        int b = 5;
        for(int i=0, y=a.length-1; i<a.length;){
            if(i == y){
                break;
            }
            if(a[i] + a[y] > b){
                y--;
            } else if(a[i] + a[y] < b){
                i++;
            } else {
                System.out.println(a[i] + " " + a[y]);
                System.out.println("Found!");
                break;
            }
        }
    }

}
