package com.theclcode.swc.newsletter.oct20.gridgame;

import java.util.Scanner;

public class GridGame {

    private int[][] grid;
    private boolean[] rows;
    private boolean[] cols;
    private int N;

    public static void main(String[] args) {
        new GridGame().run();
    }

    private void run(){
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int i = 1; i<=tc; i++){
            init(sc.nextInt(), sc);
            int ret = alice();
            System.out.format("Case #%s: %s\n", i, ret);
        }
    }

    int alice(){
        int ret = Integer.MIN_VALUE;
        boolean allCrossed = true;
        for(int i=0; i<this.N; i++){
            if(!rows[i]){
                rows[i] = true;
                int temp = bob(i);
                ret = Math.max(temp, ret);
                rows[i] = false;
                allCrossed = false;
            }
        }
        if(allCrossed){
            ret = 0;
        }
        return ret;
    }

    int bob(int i){
        int ret = Integer.MAX_VALUE;
        boolean allCrossed = true;
        for(int j=0; j<this.N; j++){
            if(!cols[j]){
                cols[j] = true;
                int temp = alice() + this.grid[i][j];
                ret = Math.min(temp, ret);
                cols[j] = false;
                allCrossed = false;
            }
        }
        if(allCrossed){
            ret = 0;
        }
        return ret;
    }


    private void init(int n, Scanner sc){
        this.grid = new int[n][n];
        this.rows = new boolean[n];
        this.cols = new boolean[n];
        this.N = n;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                this.grid[i][j] = sc.nextInt();
            }
        }
    }
}
