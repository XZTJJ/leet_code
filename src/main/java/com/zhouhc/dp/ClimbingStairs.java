package com.zhouhc.dp;

/**
 * q70_爬楼梯
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 7;
        System.out.println(findWayByDp(n));
    }
    /**
     * 动态规划
     *   通过规律可以发现 ,动态转移的转态
     *   f(i) = f(i-1)+f(i-2) ,存在临界值
     *   f(1)= 1 ,f(2) = 2的情况
     *
     *   时间复杂度 : O(n),空间复杂度 O(1)
     */
    private static int findWayByDp(int n) {
        if (n < 3)
            return n;
        //f(i-2)的值
        int prepreValue = 1;
        //f(i-1)的值
        int preVaue = 2;
        //开始计算
        for (int i = 3; i < n; i++) {
            //当前值
            preVaue = preVaue + prepreValue;
            prepreValue = preVaue - prepreValue;
        }
        //返回值
        return prepreValue + preVaue;
    }
}
