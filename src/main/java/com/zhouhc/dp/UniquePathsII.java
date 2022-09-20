package com.zhouhc.dp;

/**
 * q63_不同路径
 */
public class UniquePathsII {

    public static void main(String[] args) {
        int[][] temp = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(temp));
    }

    /**
     * dp
     * num[i][j] == 1  f[i][j]=0;
     * num[i][j] != 1  f[i][j] = f[i-1][j-1] + f[i][j-1]
     * 注意边界条件
     * f[i][0] =  num[i][0] == 1 ? 0 : f[i-1][0]
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //保存status
        int[][] status = new int[obstacleGrid.length][obstacleGrid[0].length];
        //处理边界条件
        status[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < obstacleGrid.length; i++)
            status[i][0] = obstacleGrid[i][0] == 1 ? 0 : status[i - 1][0];
        for (int j = 1; j < obstacleGrid[0].length; j++)
            status[0][j] = obstacleGrid[0][j] == 1 ? 0 : status[0][j - 1];
        //正常处理
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1)
                    status[i][j] = 0;
                else
                    status[i][j] = status[i - 1][j] + status[i][j - 1];
            }
        }
        return status[status.length - 1][status[0].length - 1];
    }

}
