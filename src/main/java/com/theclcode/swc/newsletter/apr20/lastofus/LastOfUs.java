package com.theclcode.swc.newsletter.apr20.lastofus;

import java.util.Scanner;

//Incomplete
public class LastOfUs {

    int[][] votes;
    int size;
    int ballotSize;
    int lastIndex;
    int firstPlaceVoteTotal;
    boolean[] firstVotes;
    boolean[] removed;

    public static void main(String[] args) {
        new LastOfUs().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int tc = 1; tc <= testCases; tc++) {
            int winnerId = -1;
            size = sc.nextInt();
            ballotSize = firstPlaceVoteTotal = lastIndex = size;
            votes = new int[size + 1][size + 1];
            firstVotes = new boolean[size + 1];
            removed = new boolean[size+1];
            for (int i = 1; i < votes.length; i++) {
                for (int j = 1; j < votes.length; j++) {
                    int villager = sc.nextInt();
                    if (j == 1 && !firstVotes[villager]) {
                        firstVotes[villager] = true;
                    }
                    votes[villager][j]++;
                }
            }
            while (winnerId == -1 && lastIndex > 1) {
                int maxLoser = 0;
                for (int i = 1; i < votes.length; i++) {
                    if (!removed[i] && firstVotes[i] && votes[i][1] > (firstPlaceVoteTotal / 2)) {
                        winnerId = i;
                        break;
                    }
                    if (!removed[i] && votes[i][lastIndex] > maxLoser) {
                        maxLoser = votes[i][lastIndex];
                    }
                }
                if (winnerId == -1) {
                    for (int i = 1; i < votes.length; i++) {
                        if(!removed[i] && votes[i][lastIndex] == maxLoser){
                            if(firstVotes[i]){
                                firstVotes[i] = false;
                                firstPlaceVoteTotal--;
                            }
                            removed[i] = true;
                        }
                    }
                }
                lastIndex--;
            }
            System.out.println("Case #" + tc + ": " + winnerId);
        }
    }
}
