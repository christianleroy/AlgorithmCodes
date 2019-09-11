package com.theclcode.swc.intermediate.bixby;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int i=1; i<=testCases; i++){
            int numberOfSongs = sc.nextInt();

            int[][] songs = new int[numberOfSongs][];
            for(int j=0; j<numberOfSongs; j++){
                int songLength = sc.nextInt();
                songs[j]=new int[songLength];
                for(int k=0; k<songLength; k++){
                    songs[j][k]= sc.nextInt();
                }
            }
            int[] segmentA = new int[sc.nextInt()];
            int[] segmentC = new int[sc.nextInt()];

            for(int x=0; x<segmentA.length; x++){
                segmentA[x] = sc.nextInt();
            }

            for(int y=0; y<segmentC.length; y++){
                segmentC[y] = sc.nextInt();
            }
            boolean matched = false;
            for(int a=0; a<numberOfSongs; a++){
                Result r = findInSong(songs[a], segmentA, 0);
                if(r.isFound()){
                    r = findInSong(songs[a], segmentC, r.getIndex()+segmentA.length+1);
                    if(r.isFound()){
                        System.out.print("Case #"+i+": "+(a+1));
                        matched=true;
                        break;
                    }
                }
            }
            if(!matched){
                System.out.print("Case #"+i+": -1");
            }
            System.out.println();
        }
	}

	public static Result findInSong(int[] song, int[] segment, int beginIndex){
        Result r = new Result();
        r.setFound(false);
        for(int i=beginIndex; i<song.length; i++) {
            if (segment[0] != song[i]) {
                continue;
            }
            int x=1, y=i+1;
            for (x=1, y=i+1; x<segment.length && y<song.length; x++, y++) {
                if (segment[x] != song[y]) {
                    return r;
                }
            }
            if(x==segment.length){
                r.setFound(true);
            }
            r.setIndex(i);
            return r;
        }
        return r;
    }

    public static class Result {
        int index;
        boolean found;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isFound() {
            return found;
        }

        public void setFound(boolean found) {
            this.found = found;
        }
    }
}
