package com.theclcode.array.medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];


        matrix[0] = new int[]{1,2,3,4};
        matrix[1] = new int[]{5,6,7,8};
        matrix[2] = new int[]{9,10,11,12};
        matrix[3] = new int[]{13,14,15,16};

        List<Integer> result = spiralOrder(matrix);
        System.out.println(result);

    }
    public static List<Integer> spiralOrder(int[][] matrix) {

        if(matrix.length == 0){
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        traverse(matrix, 0, 0, 1, result);
        return result;
    }

    public static void traverse(int[][] matrix, int x, int y, int move, List<Integer> result) {


        if((x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) || (matrix[x][y] == 101)) {
            return;
        }

        result.add(matrix[x][y]);
        matrix[x][y]=101;

        if(move == 1) {
            if(y + 1 < matrix[0].length && matrix[x][y + 1] != 101) {
                traverse(matrix, x, y+1, move, result);
            } else {
                traverse(matrix, x + 1, y, 2, result);
            }
        } else if(move == 2) {
            if(x + 1 < matrix.length && matrix[x + 1][y] != 101) {
                traverse(matrix, x + 1, y, move, result);
            } else {
                traverse(matrix, x, y -1, 3, result);
            }
        } else if(move == 3) {
            if(y - 1 >= 0 && matrix[x][y-1] != 101) {
                traverse(matrix, x, y-1, move, result);
            } else {
                traverse(matrix, x-1, y, 4, result);
            }
        } else if(move == 4) {
            if(x - 1 >= 0 && matrix[x-1][y] != 101) {
                traverse(matrix, x-1, y, move, result);
            } else {
                traverse(matrix, x, y + 1, 1, result);
            }
        }
    }
}
