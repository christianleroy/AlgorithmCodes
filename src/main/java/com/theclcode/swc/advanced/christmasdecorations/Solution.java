package com.theclcode.swc.advanced.christmasdecorations;

import java.util.Scanner;

public class Solution {

    static int reds, blues, yellows, max;
    static int[][] decorationModels;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int tc=1; tc<=testCases; tc++){
            int numberOfDecorations = sc.nextInt();
            reds = sc.nextInt();
            blues = sc.nextInt();
            yellows = sc.nextInt();
            max=0;
            decorationModels = new int[numberOfDecorations][3];
            for(int i=0; i<numberOfDecorations; i++){
                for(int j=0; j<3; j++){
                    decorationModels[i][j]=sc.nextInt();
                }
            }
            permute(decorationModels, 0, decorationModels.length-1);
            System.out.println("#"+tc+" "+max);
        }
    }

    public static void produceDecorationModels(int model, int limit, int produced, int reds, int blues, int yellows){
        if(model==decorationModels.length){
            if(produced>max){
                max=produced;
            }
            return;
        }
        int redsCopy = reds;
        int bluesCopy = blues;
        int yellowsCopy = yellows;
        int producedCopy = produced;
        for(int j=limit; j<=3; j++){
            if(redsCopy>=decorationModels[model][0] && bluesCopy>=decorationModels[model][1] && yellowsCopy>=decorationModels[model][2]){
                redsCopy-=decorationModels[model][0];
                bluesCopy-=decorationModels[model][1];
                yellowsCopy-=decorationModels[model][2];
                producedCopy++;
            }
            produceDecorationModels(model+1, 1, producedCopy, redsCopy, bluesCopy, yellowsCopy);
        }
    }

    public static void swap(int[][] dms, int left, int right){
        int[] temp = dms[right];
        dms[right] = dms[left];
        dms[left] = temp;
    }

    public static void permute(int[][] dms, int start, int end){
        if(start==end){
            produceDecorationModels(0, 1, 0, reds, blues, yellows);
            return;
        }
        for(int i=start; i<=end; i++){
            swap(dms, start, i);
            permute(dms, start+1, end);
            swap(dms, start, i);
        }
    }
}
