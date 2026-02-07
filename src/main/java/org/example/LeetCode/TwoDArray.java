package org.example.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoDArray {

    public static int[][] rotate(int[][] arr) {
        int len = arr.length;
        int[][] result = new int[len][len];
        for(int i=0;i<len;i++) {
            for(int j=0;j<len;j++) {
                result[j][len-i-1] = arr[i][j];
            }
        }
        return result;
    }

    public static int[][] rotateImage(int[][] matrix) {
        int n = matrix.length;
        /// transpose the matrix first
        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                swap(matrix,i,j);
            }
        }
        /// then reverse each rows by swapping means j -> n/2

        for(int i=0;i<n;i++) {
            for(int j=0;j<n/2;j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
        return matrix;
    }
    public static void swap(int[][] matrix,int i,int j){
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }


    /// count the unguarded cells
    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] cells = new int[m][n];
        for (int[] guard : guards) {
            int s1 = guard[0], s2 = guard[1];
            cells[s1][s2] = 1;
        }
        for (int[] wall : walls) {
            int s1 = wall[0], s2 = wall[1];
            cells[s1][s2] = 2;

        }
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}}; ///{down,up,right,left}
        for(int[] g: guards){
            for(int[] d : dirs){
                int x= g[0] + d[0];
                int y = g[1] + d[1];
                while(x>=0 && y>=0 && x<m && y<n) {
                    if(cells[x][y]==0) cells[x][y] = 3;
                    if(cells[x][y]==1 || cells[x][y]==2) break;
                    x += d[0];
                    y += d[1];
                }
            }
        }
        int count=0;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++){
                if(cells[i][j]==0) count++;
            }
        }

        return count;

    }

    public static List<Integer> spiralOrder(int[][] matrix) {
       int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

       List<Integer> list = new ArrayList<>();

       int m = matrix.length;
       int n = matrix[0].length;

       boolean[][] visited =  new boolean[m][n];

       int x=0,y=0;
       int d=0;
       for(int i=0;i<(m*n);i++){
           list.add(matrix[x][y]);
           visited[x][y] = true;

           int nx = x+dirs[d][0];
           int ny = y+dirs[d][1];

           if(nx<0 || ny<0 || nx>=m || ny>=n||visited[nx][ny]) {
               d = (d+1)%4;
           }

           x += dirs[d][0];
           y += dirs[d][1];
       }

       return list;


    }

    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(rotateImage(new int[][]{
//
//                {1, 3, 5},
//                {7, 9, 11},
//                {13, 15, 17}})));

        int m = 4, n = 6;
        int[][] guards = {{0,0}, {1,1}, {2,3}};
        int[][] walls = {{0,1}, {2,2}, {1,4}};

        //System.out.println(countUnguarded(m, n, guards, walls));
        System.out.println(spiralOrder(new int[][]{

                {1, 3, 5},
                {7, 9, 11},
                {13, 15, 17}}));


    }
    }

