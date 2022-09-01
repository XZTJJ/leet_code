package com.zhouhc.dp;

/**
 * dp: 只需要求 f(n), f(n-1)的最小值就好了
 * f(n) = min(f(n-1),f(n-2)) + n
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int[] maxStatus = new int[cost.length];
        maxStatus[0] = cost[0];
        maxStatus[1] = cost[1];
        //求状态，转台方程式为 : maxStatus[i] = min(maxStatus[i-1],maxStatus[i-2]) + num[i]
        for (int i = 2; i < cost.length; i++)
            maxStatus[i] = Math.min(maxStatus[i - 1], maxStatus[i - 2]) + cost[i];
        //最小值
        return Math.min(maxStatus[cost.length - 1], maxStatus[cost.length - 2]);
    }
}
