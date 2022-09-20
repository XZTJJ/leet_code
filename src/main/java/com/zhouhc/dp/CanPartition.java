package com.zhouhc.dp;

/**
 * q416_分割等和子集
 */
public class CanPartition {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{14, 9, 8, 4, 3, 2}));
    }

    /**
     * dp思想, 01背包问题，只不过这次是求最大值的一半，
     * 相等的子集，其实就是求和的一半。
     * 然后就就可以求: 最大值的一半(必须是偶数才行，并且最大值不能超过一半)
     * i表示到目前为止的物品，j表示背包的容量(j只需要target就好了),为了方便计算，直接让物品重量等于
     * 物品价值，dp[i][j]使用bool表示，不能表示为最大值，最大值不一定就是适合的值
     * 所以状态方程式：
     * db[i][j] = dp[i-1][j] || db[i-1][j-num[i]]  j > num[i]
     * db[i][j] = dp[i-1][j]                     j < num[i]
     */
    public static boolean canPartition(int[] nums) {
        //先判断
        int sum = 0;
        int maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(num, maxNum);
        }
        //剪枝操作
        int half = sum / 2;
        if (sum % 2 != 0 || maxNum > half)
            return false;
        //初始化,都要+1，方便处理没有物品和背包容量为0的情况
        boolean[][] status = new boolean[nums.length + 1][half + 1];
        //边界条件，表示背包的重量为0，不管物品，所有的都满足true的情况
        for (int i = 0; i < nums.length + 1; i++)
            status[i][0] = true;
        //边界条件，i为0时，表示没有任务物品，不管背包容量多少，都为false
        for (int j = 1; j < half + 1; j++)
            status[0][j] = false;
        //开始遍历，先遍历书包，后遍历物品，都是从1开始，因为为0的边界条件已经处理了
        for (int i = 1; i < nums.length + 1; i++) {
            //表示背包的容量
            for (int j = 1; j < half + 1; j++) {
                //背包重量小于物品重量的情况,下面的所有num[i-1]，因为这里的i是从1开始，而不是从0开始的
                if (j < nums[i - 1])
                    status[i][j] = status[i - 1][j];
                else
                    status[i][j] = status[i - 1][j] || status[i - 1][j - nums[i - 1]];
                //表示符合条件了
                if (j == half && status[i][j])
                    return true;
            }
        }
        return false;
    }
}
