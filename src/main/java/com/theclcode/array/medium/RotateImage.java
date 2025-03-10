package com.theclcode.array.medium;


// https://leetcode.com/problems/rotate-image/description/
public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printMatrix(matrix);
        transpose(matrix);
        printMatrix(matrix);
        reverseRows(matrix);
        printMatrix(matrix);
    }

    static void transpose(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = i + 1; j < matrix[i].length; j++) {
                swap(matrix, i, j);
            }
        }
    }

    static void reverseRows(int[][] matrix) {
        for (int[] row : matrix) {
            for (int i = 0, j = row.length - 1; i < row.length / 2; i++, j--) {
                int temp = row[i];
                row[i] = row[j];
                row[j] = temp;
            }
        }

    }

    static void swap(int[][] matrix, int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
