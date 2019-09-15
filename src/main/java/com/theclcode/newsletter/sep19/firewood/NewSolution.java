package com.theclcode.newsletter.sep19.firewood;

import java.util.Scanner;

public class NewSolution {

    static ForestObject[] trees;
    static ForestObject[] fires;
    static int[][] forest;
    static ForestObject[] treesCombinationStack;
    static int treesCombinationStackMarker;
    static int treeCutLimit=0;
    static int maxTrees;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int tc=1; tc<=testCases; tc++){
            int size = sc.nextInt();
            trees = new ForestObject[size*size];
            fires = new ForestObject[size*size];
            forest = new int[size][size];
            treesCombinationStack = new ForestObject[2];
            treesCombinationStackMarker = 0;
            treeCutLimit = sc.nextInt();
            maxTrees=0;
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
                System.out.println("Case #"+tc+": "+treesMarker);
            } else {
                int defaultMaxTrees = simulateFirePath(copyArray(), firesMarker, treesMarker);
                getMaxTrees(firesMarker, treesMarker, treeCutLimit);
                System.out.println("Case #"+tc+": "+(defaultMaxTrees > maxTrees ? defaultMaxTrees : maxTrees));
            }
        }
    }

    private static int getMaxTrees(int fires, int remainingTrees, int treeCutLimit) {
        for(int i=1; i<=treeCutLimit; i++){
            treesCombinationStack = new ForestObject[i];
            treesCombinationStackMarker=0;
            combination(trees, 0, i, fires, remainingTrees);
        }
        return 0;
    }

    public static int simulateFirePath(int[][] forestCopy, int firesCount, int treesCount){
        for(int i=0; i<firesCount; i++){
            ForestObject[] stack = new ForestObject[forestCopy.length*forestCopy.length];
            int marker=0;
            stack[marker++] = fires[i];
            while(marker>0){
                ForestObject fire = stack[--marker];
                stack[marker]=null;
                int x=fire.getX();
                int y=fire.getY();
                if(forestCopy[x][y]==1){
                    treesCount--;
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
        }
        return treesCount;
    }

    public static void combination(ForestObject[] trees, int start, int limit, int firesCount, int treesCount){
        if(limit==0){
            int[][] forestCopy = copyArray();
            for(int i=0; i<treesCombinationStackMarker; i++){
                ForestObject tree = treesCombinationStack[i];
                forestCopy[tree.getX()][tree.getY()]=0;
                treesCount--;
            }
            int remainingTrees = simulateFirePath(forestCopy, firesCount, treesCount);
            if(remainingTrees>maxTrees){
                maxTrees=remainingTrees;
            }
            return;
        }

        for(int i=start;i<treesCount-limit; i++){
            treesCombinationStack[treesCombinationStackMarker++] = trees[i];
            combination(trees, i+1, limit-1, firesCount, treesCount);
            treesCombinationStack[--treesCombinationStackMarker]=null;
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
}
