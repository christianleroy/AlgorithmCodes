package com.theclcode.swc.newsletter.nov20.magicsquare;

import java.util.Scanner;

public class MagicSquare {

    public static final String NO_MAGIC = "No Magic :(";
    private char[][] square;
    private char[] word = new char[10_000];
    private int length;

    public static void main(String[] args) {
        new MagicSquare().run();
    }

    private void init() {
        for (int i = 0; i < word.length && word[i] != '\0'; i++) {
            this.word[i] = '\0';
        }
        this.length = 0;
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int tc = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= tc; i++) {
            init();
            String output = null;
            String input = sc.nextLine();
            for (int j = 0, k = 0; j < input.length(); j++) {
                if (input.charAt(j) >= 97 && input.charAt(j) <= 122) {
                    this.word[k++] = input.charAt(j);
                    this.length++;
                }
            }
            double squareLength = Math.sqrt(this.length);
            if(squareLength == Math.floor(squareLength)){
                this.square = new char[(int)squareLength][(int)squareLength];
                int w = 0;
                for(int x = 0; x < squareLength; x++){
                    for(int y = 0; y < squareLength; y++){
                        this.square[x][y] = this.word[w];
                        w++;
                    }
                }
                output = magicTraversal((int) squareLength-1);
            } else {
                output = NO_MAGIC;
            }
            System.out.format("Case #%s: %s\n", i, output);
        }
    }

    private String magicTraversal(int end){
        for(int i=0; i <= end; i++){
            for(int j=0; j <= end; j++){
                if(this.square[i][j] != this.square[end - i][end - j]){
                    return NO_MAGIC;
                }
                if(this.square[i][j] != this.square[j][i]){
                    return NO_MAGIC;
                }
                if(this.square[i][j] != this.square[end - j][end - i]){
                    return NO_MAGIC;
                }
            }
        }
        return String.valueOf(++end);
    }

}
