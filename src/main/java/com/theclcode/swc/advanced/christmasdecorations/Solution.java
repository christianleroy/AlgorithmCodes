package com.theclcode.swc.advanced.christmasdecorations;

import java.util.Scanner;

public class Solution {

    static int reds, blues, yellows, max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int tc=1; tc<=testCases; tc++){
            int numberOfDecorations = sc.nextInt();
            reds = sc.nextInt();
            blues = sc.nextInt();
            yellows = sc.nextInt();
            max=0;
            int[][] decorationModels = new int[numberOfDecorations][3];
            for(int i=0; i<numberOfDecorations; i++){
                for(int j=0; j<3; j++){
                    decorationModels[i][j]=sc.nextInt();
                }
            }
            permute(decorationModels, 0, decorationModels.length-1);
            System.out.println("#"+tc+" "+max);
        }
    }

    public static void swap(int[][] dms, int left, int right){
        int[] temp = dms[right];
        dms[right] = dms[left];
        dms[left] = temp;
    }

    public static void permute(int[][] dms, int start, int end){
        if(start==end){
            int produced = produceDecorationModels(dms, reds, blues, yellows);
            if(produced>max){
                max = produced;
            }
            return;
        }
        for(int i=0; i<=end; i++){
            swap(dms, i, start);
            permute(dms, start+1, end);
            swap(dms, i, start);
        }
    }

    public static int produceDecorationModels(int[][] dms, int reds, int blues, int yellows){
        int produced=0;
        for(int i=0; i<dms.length; i++){
            int limit=3;
            if(limit>0 && reds>=dms[i][0] && blues>=dms[i][1] && yellows>=dms[i][2]){
                reds-=dms[i][0];
                blues-=dms[i][1];
                yellows-=dms[i][2];
                limit--;
                produced++;
            }
        }
        return produced;
    }
}
