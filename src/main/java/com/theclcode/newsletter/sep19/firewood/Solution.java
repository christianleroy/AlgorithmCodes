package com.theclcode.newsletter.sep19.firewood;

import java.util.Scanner;

public class Solution {

    public static int trees=0;
    public static Thing[] firesArray = new Thing[81];
    public static int firesArrayStack = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCasers = sc.nextInt();
        for(int i=0; i<testCasers; i++){
            init();
            int gridSize = sc.nextInt();
            int treesToCut = sc.nextInt();
            int thingNum = sc.nextInt();
            int[][] forest = buildForest(thingNum, gridSize);
            if(firesArrayStack==0){
                System.out.println(trees);
            } else {
                getMaxTrees(forest, treesToCut);
            }
        }
    }

    public static int getMaxTrees(int[][] forest, int treesToCut){
        int[][] forestCopy = copyArray(forest);
        for(int i=0; i<firesArray.length; i++){

        }
        return 0;
    }

    public static void findPath(int[][] forest, int x, int y){
        int yCopy=0;
        int xCopy=0;
        if(y>0){
            yCopy=y;
            while(yCopy>0 && forest[x][yCopy-1]==1){
                forest[x][yCopy-1]=0;
                yCopy--;
            }
        }
        if(x<forest.length){
            xCopy=x;
            while(xCopy<forest.length && forest[xCopy+1][y]==1){
                forest[xCopy+1][y]=0;
                xCopy++;
            }
        }
        if(y<forest.length){
            yCopy=y;
            while(yCopy<forest.length && forest[x][yCopy+1]==1){
                forest[x][yCopy+1]=0;
                yCopy++;
            }
        }
        if(x>0){
            xCopy=x;
            while(xCopy>0 && forest[xCopy-1][y]==1){
                forest[xCopy-1][y]=0;
                xCopy--;
            }
        }
    }

    private static int[][] buildForest(int thingNum, int gridSize) {
        int[][] forest = new int[gridSize][gridSize];
        for(int j=0; j<gridSize; j++){
            for(int k=0; k<gridSize; k++){
                if(thingNum==1){
                    trees++;
                } else if(thingNum==2){
                    Thing thing = new Thing(j, k, Type.FIRE);
                    firesArray[firesArrayStack++] = thing;
                }
                forest[j][k]=thingNum;
                System.out.print(forest[j][k]+" ");
            }
            System.out.println();
        }
        return forest;
    }

    private static int[][] copyArray(int[][] forest) {
        int[][] forestCopy = new int[forest.length][forest.length];
        for(int j=0; j<forest.length; j++) {
            for (int k = 0; k < forest.length; k++) {
                forestCopy[j][k]=forest[j][k];
            }
        }
        return forestCopy;
    }
    private static void init() {
        trees=0;
        firesArray = new Thing[81];
        firesArrayStack = 0;
    }
    public enum Type{
        TREE,
        FIRE,
        EMPTY
    }
    public static class Thing {
        int x;
        int y;
        Type type;

        public Thing(int x, int y, Type type){
            this.x=x;
            this.y=y;
            this.type=type;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }
}
