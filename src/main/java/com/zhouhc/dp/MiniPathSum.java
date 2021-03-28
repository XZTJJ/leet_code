package com.zhouhc.dp;

/**
 * q64_最小路径和
 */
public class MiniPathSum {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(findWayByDP(grid));
    }

    /**
     * 动态规划:
     *  这题和  q62_不同路径 类似，62题是求
     * 总共的路径，这里是求最小路径和。就是在62
     * 基础上，始终保留最小的那条路径就好
     *  转态转换方式
     *    f(i,j) = f(i+1,j)+f(i,j+1)
     *
     * 空间复杂度 : O(mn) 空间复杂度:O(1) 利用原来内存空间就好
     */
    private static int findWayByDP(int[][] grid) {
        //如果不能再原数组上修改的话，可以创建一个grid[0]长度的数组就行了
        int row = grid.length - 1;
        int col = grid[0].length - 1;
        //处理最后一行的临界值的情况
        for (int j = col - 1; j >= 0; j--) {
            grid[row][j] += grid[row][j + 1];
        }
        //处理最边的一行
        for (int i = row - 1; i >= 0; i--)
            grid[i][col] += grid[i + 1][col];

        //处理不是最后一行，最右一行的情况
        for (int i = row - 1; i >= 0; i--) {
            //求每一层的值
            for (int j = col - 1; j >= 0; j--) {
                //除了倒数第一行的求法,其他行为  f(i,j+1)+f(i+1,j)最小值,需要考虑最下面和最上面的临界值
                //f(i+1,j) 值
                int bottom = grid[i + 1][j];
                //如果是最右行，直接加下面的值就好
                int right = grid[i][j + 1];
                //始终保持最少值,最下面的那个元素保持原值
                grid[i][j] += Math.min(bottom, right);
            }
        }
        return grid[0][0];
    }

}
