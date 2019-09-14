package com.theclcode.newsletter.sep19.firewood;

import java.util.Scanner;

public class NewSolution {

    static ForestObject[] trees;
    static ForestObject[] fires;
    static ForestObject[] treesStack;
    static int treesStackMarker;
    static int[][] forest;
    static int treeCutLimit=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int tc=1; tc<=testCases; tc++){
            int size = sc.nextInt();
            forest = new int[size][size];
            treeCutLimit = sc.nextInt();
            fires = new ForestObject[size*size];
            trees = new ForestObject[size*size];
            int treesMarker=0, firesMarker=0;
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    forest[i][j] = sc.nextInt();
                    if(forest[i][j]==2){
                        fires[firesMarker++] = new ForestObject(i, j);
                    } else if(forest[i][j]==1){
                        trees[treesMarker++] = new ForestObject(i, j);
                    }
                }
            }
            if(firesMarker==0){
                System.out.print("Case #"+tc+": "+treesMarker);
            } else {
                int maxTrees = simulateFirePath(copyArray(), firesMarker, treesMarker);
            }
        }
    }

    private static int getMaxTrees(int fires, int remainingTrees) {
        treesStack = new ForestObject[remainingTrees];
        treesStackMarker=0;
        return 0;
    }

    public static int simulateFirePath(int[][] forestCopy, int firesCount, int treesCount){
        for(int i=0; i<firesCount; i++){
            ForestObject[] stack = new ForestObject[4];
            int marker=0;
            stack[marker++] = fires[i];
            while(marker>0){
                ForestObject fire = stack[--marker];
                int x=fire.getX();
                int y=fire.getY();
                if(forestCopy[x][y]==1){
                    treesCount--;
                    print(forestCopy);
                }
                forestCopy[x][y]=0;
                if(y<forestCopy.length-1 && forestCopy[x][y+1]==1){
                    stack[marker++]=new ForestObject(x, y+1);
                }
                if(y>0 && forestCopy[x][y-1]==1){
                    stack[marker++]=new ForestObject(x, y-1);
                }
                if(x<forestCopy.length-1 && forestCopy[x+1][y]==1){
                    stack[marker++]= new ForestObject(x+1, y);
                }
                if(x>0 && forestCopy[x-1][y]==1){
                    stack[marker++]= new ForestObject(x-1, y);
                }
            }
            print(forestCopy);
        }
        return treesCount;
    }

    public static void combination(ForestObject[] trees, int start, int limit){
        if(limit==0){
            return;
        }

        for(int i=start;i<trees.length-limit; i++){

        }
    }
    public static int[][] copyArray(){
        int[][] copy = new int[forest.length][forest.length];
        for(int x=0; x<forest.length; x++){
            for(int y=0; y<forest.length; y++){
                copy[x][y] = forest[x][y];
            }
        }
        return copy;
    }
    public static class ForestObject {
        private int x;
        private int y;
        ForestObject(int x, int y){
            this.x=x;
            this.y=y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    private static void print(int[][] a){
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a.length; j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
