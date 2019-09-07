package com.theclcode.newsletter.sep19.firewood;

import java.util.Scanner;

public class Solution {

    public static Thing[] firesArray;
    public static int firesArrayStack;
    public static Thing[] treesArray;
    public static int treesArrayStack;
    public static Thing[] treesCombinationStack;
    public static int treesCombinationStackMarker;
    public static int maxTrees;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCasers = sc.nextInt();
        for(int i=1; i<=testCasers; i++){
            init();
            int gridSize = sc.nextInt();
            int treesToCut = sc.nextInt();
            int[][] forest = new int[gridSize][gridSize];

            for(int x=0; x<gridSize; x++){
                for(int y=0; y<gridSize; y++){
                    forest[x][y]=sc.nextInt();
                    if(forest[x][y]==1){
                        Thing tree = new Thing(x, y, Type.TREE);
                        treesArray[treesArrayStack++]=tree;
                    } else if(forest[x][y]==2){
                        Thing fire = new Thing(x, y, Type.FIRE);
                        firesArray[firesArrayStack++]=fire;
                    }
//                    System.out.print(forest[x][y]);
                }
//                System.out.println();
            }

            if(firesArrayStack==0){
                System.out.println("Case #"+i+": "+treesArrayStack);
            } else {
                getMaxTree(forest, treesToCut);
                System.out.println("Case #"+i+": "+maxTrees);
            }
        }
    }

    public static int getMaxTree(int[][] forest, int treesToCut){
        int[][] forestCopy = copyArray(forest);
        maxTrees = initFirePathSimulation(forestCopy, treesArrayStack);
        for(int i=1; i<=treesToCut; i++){
            treesCombinationStack = new Thing[treesToCut];
            treesCombinationStackMarker=0;
            cutTreesAndSimulateFirePath(treesArray, forest,0, i);
        }
        return 0;
    }

    public static void cutTreesAndSimulateFirePath(Thing[] trees, int[][] forest, int start, int limit){
        if(limit==0){
            cutTrees(treesCombinationStack, treesCombinationStackMarker, forest);
            return;
        }
        for(int i=start; i<=treesArrayStack-limit; i++){
            treesCombinationStack[treesCombinationStackMarker++]=trees[i];
            cutTreesAndSimulateFirePath(trees, forest, i+1, limit-1);
            treesCombinationStack[--treesCombinationStackMarker]=null;
        }
    }

    public static void cutTrees(Thing[] treesToCutCombination, int treesCombinationStackMarker, int[][] forest){
        int[][] forestCopy = copyArray(forest);
        int numberOfTrees = treesArrayStack;
        for(int i=0; i<treesCombinationStackMarker; i++){
            forestCopy[treesToCutCombination[i].getX()][treesToCutCombination[i].getY()]=0;
            numberOfTrees--;
        }
        numberOfTrees = initFirePathSimulation(forestCopy, numberOfTrees);

        if(numberOfTrees>maxTrees){
            maxTrees=numberOfTrees;
        }
    }

    private static int initFirePathSimulation(int[][] forestCopy, int numberOfTrees) {
        for(int i=0; i<firesArrayStack; i++){
            numberOfTrees=findPath(forestCopy, firesArray[i].getX(), firesArray[i].getY(), numberOfTrees);
        }
        return numberOfTrees;
    }

    public static int findPath(int[][] forest, int x, int y, int numberOfTrees){
        int yCopy=0;
        int xCopy=0;
        if(y>0){
            yCopy=y;
            while(yCopy>0 && forest[x][yCopy-1]==1){
                forest[x][yCopy-1]=0;
                numberOfTrees--;
                numberOfTrees = findPath(forest,x, yCopy-1, numberOfTrees);
                yCopy--;
            }
        }
        if(x<forest.length){
            xCopy=x;
            while(xCopy+1<forest.length && forest[xCopy+1][y]==1){
                forest[xCopy+1][y]=2;
                numberOfTrees--;
                numberOfTrees = findPath(forest,xCopy+1, y, numberOfTrees);
                xCopy++;
            }
        }
        if(y<forest.length){
            yCopy=y;
            while(yCopy+1<forest.length && forest[x][yCopy+1]==1){
                forest[x][yCopy+1]=0;
                numberOfTrees--;
                numberOfTrees = findPath(forest,x, yCopy+1, numberOfTrees);
                yCopy++;
            }
        }
        if(x>0){
            xCopy=x;
            while(xCopy>0 && forest[xCopy-1][y]==1){
                forest[xCopy-1][y]=0;
                numberOfTrees--;
                numberOfTrees = findPath(forest,xCopy-1, y, numberOfTrees);
                xCopy--;
            }
        }
        return numberOfTrees;
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
        firesArray = new Thing[81];
        firesArrayStack = 0;
        treesArray = new Thing[81];
        treesArrayStack = 0;
        maxTrees=0;
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
