package com.theclcode.matrix;

public class MatrixTraversal {
    // Down Right Up Left
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        traverse(0,0,matrix,visited);

    }


    public static void traverse(int x, int y, int[][] matrix, boolean[][] visited) {

        if(isValid(x,y,matrix,visited)) {
            System.out.print(matrix[x][y] + " ");
            visited[x][y] = true;
            for(int i = 0; i < 4; i++) {
                traverse(x + dx[i], y + dy[i], matrix, visited);
            }
        }

    }

    public static boolean isValid(int x, int y, int[][] matrix, boolean[][] visited) {
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y]) {
            return false;
        }
        return true;
    }
}
