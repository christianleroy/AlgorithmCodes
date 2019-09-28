package com.theclcode.swc.advanced.christmasdecorations;

import java.util.Scanner;

public class Solution {

    static int max, produced;
    static int[][] decorationModels;
    static int[] supply;
    static int[] modelCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int tc=1; tc<=testCases; tc++){
            int numberOfDecorations = sc.nextInt();
            supply = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
            max = produced = 0;
            decorationModels = new int[numberOfDecorations][3];
            modelCount = new int[numberOfDecorations];
            for(int i=0; i<numberOfDecorations; i++){
                for(int j=0; j<3; j++){
                    decorationModels[i][j]=sc.nextInt();
                }
            }
            traverse(0);
            System.out.println("#"+tc+" "+max);
        }
    }

    static void traverse(int start){
        for(int i=start; i<decorationModels.length; i++){
            if(modelCount[i] < 3 && canProduce(i)){
                produce(i);
                traverse(i);
                remove(i);
            }
        }
    }

    static void produce(int model){
        supply[0]-=decorationModels[model][0];
        supply[1]-=decorationModels[model][1];
        supply[2]-=decorationModels[model][2];
        modelCount[model]+=1;
        produced+=1;
    }

    static void remove(int model){
        supply[0]+=decorationModels[model][0];
        supply[1]+=decorationModels[model][1];
        supply[2]+=decorationModels[model][2];
        modelCount[model]-=1;
        if(produced>max){
            max=produced;
        }
        produced-=1;
    }

    static boolean canProduce(int model){
        if(decorationModels[model][0] <= supply[0] && decorationModels[model][1] <=supply[1]
                && decorationModels[model][2] <= supply[2]){
            return true;
        }
        return false;
    }
}
