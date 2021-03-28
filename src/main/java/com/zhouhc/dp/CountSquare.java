package com.zhouhc.dp;

/**
 * q1277_统计全为 1 的正方形子矩阵
 */
public class CountSquare {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        System.out.println(findWayByDp(matrix));
    }

    /**
     * 动态规划
     *  官方思想 :
     *     对于f[i][j]而言，不仅是边长为x的正方形，
     * 更加是在f[i][j]处有x个个正方形，从f[i][j]而言
     * f[i-1][j-1] , f[i-1][j] , f[i][j-1]这三处
     * 决定了能构建多少个正方形。状态转移方程式为 :
     *  f[i][j] (matrix为给定的对应数组) :
     *    1. i=0||j=0 ,  就为matrix[i][0],matrix[0][j]
     *    2. 0     ,   matrix[i][j] == 0
     *    3. min{ matrix[i-1][j-1] ,
     *    matrix[i-1][j] , matrix[i][j-1] } + 1
     */
    private static int findWayByDp(int[][] matrix) {
        //保存转态
        int[][] status = new int[matrix.length][matrix[0].length];
        //保存正方形数据量
        int maxCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //保存数据
                if (i == 0 || j == 0)
                    status[i][j] = matrix[i][j];
                else if (matrix[i][j] == 0)
                    status[i][j] = 0;
                else
                    status[i][j] = Math.min(status[i][j - 1], Math.min(status[i - 1][j - 1], status[i - 1][j])) + 1;
                //统计最大值
                maxCount += status[i][j];
            }
        }
        return maxCount;
    }

}
