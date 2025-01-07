package com.theclcode.array.medium;

// https://leetcode.com/problems/set-matrix-zeroes/description/
public class SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        int[][] matrix2 = new int[][]{{0,1}};
        int[][] matrix3 = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix4 = new int[][]{{1,1,0},{1,1,0},{1,1,1}};

//          setZeroes(matrix);
        setZeroesConstantSpace(matrix4);
    }

    // First, take note if 0 is found in first row or column. Flag it if yes.
    // Marks the first row and column for indexes where 0 is found. For example, zero found in 2,1, will mark 0,1 and 2,0
    // Then goes through each row, and if 0 is found, make that whole row as 0.
    // Then goes through each column, and if 0 is found, make that whole column as 0.
    // Finally, flag from first step is set, set either whole first row or whole first column or both to zero.
    public static void setZeroesConstantSpace(int[][] matrix) {

        printMatrix(matrix);
        boolean hasZeroesOnFirstRow = false;
        boolean hasZeroesOnFirstColumn = false;

        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == 0) {
                hasZeroesOnFirstColumn = true;
            }
        }

        for(int j = 0; j < matrix[0].length; j++) {
            if(matrix[0][j] == 0) {
                hasZeroesOnFirstRow = true;
            }
        }

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i = 1; i < matrix.length; i++) {
            if(matrix[i][0] == 0) {
                for(int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for(int j = 1; j < matrix[0].length; j++) {
            if(matrix[0][j] == 0) {
                for(int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }


        if(hasZeroesOnFirstRow) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }

        if(hasZeroesOnFirstColumn) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        printMatrix(matrix);
    }

    public static void setZeroes(int[][] matrix) {

        int xBound = matrix.length;
        int yBound = matrix[0].length;
        boolean[][] visited = new boolean[xBound][yBound];

        printMatrix(matrix);
        traverseMatrix(matrix, visited);
        printMatrix(matrix);
    }

    public static void convert(int i, int j, int[][] matrix, boolean[][] visited) {

        if(!visited[i][j] && matrix[i][j] != 0) {
            visited[i][j] = true;
            matrix[i][j] = 0;
        }
    }

    public static void traverseMatrix(int[][] matrix, boolean[][] visited) {

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0 && !visited[i][j]) {
                    int _i = i;
                    int _j = j;
                    while(_i-1 >= 0) {
                        convert(--_i, j, matrix, visited);
                    }
                    while(_i+1 < matrix.length) {
                        convert(++_i, j, matrix, visited);
                    }
                    while(_j-1 >= 0) {
                        convert(i, --_j, matrix, visited);
                    }
                    while(_j+1 < matrix[0].length) {
                        convert(i, ++_j, matrix, visited);
                    }
                }

            }
        }

    }

    public static void printMatrix(int[][] matrix) {

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

}
