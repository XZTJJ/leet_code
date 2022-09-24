package com.zhouhc.dp;


/**
 * q494_目标和
 */
public class findTargetSumWays {

    public static void main(String[] args) {
        int targetSumWays = new findTargetSumWays().findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1);
        System.out.println(targetSumWays);
    }


    /**
     * dp问题,背包问题，同样是分成两堆，其中两堆的总量差是
     * target就好了，背包的总量就为: (sum - target)/2，
     * 所以status中存的就是数量，其中0可以 +/- ，都没有关系
     * 所以 0 可以加两遍，并且初始值status[0][0]，
     * 类似路径问了，所以使用的是 加法，不是最大值了。
     * 状态方程式 :
     * db[i][j] = db[i-1][j]+ db[i-1][j - num[i]]  j>=num[i]
     * db[i][j] = db[i-1][j]     j < num[i]
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum - target < 0 || (sum - target) % 2 != 0)
            return 0;
        int packageWeight = (sum - target) / 2;
        int[][] status = new int[nums.length + 1][packageWeight + 1];
        /*
         * 边界条件，注意status[0][0]=1 ,其他的status[0][j]为0,
         * 注意因为存在 num[i] 为0的情况，所以status[i][0]就不能
         * 直接为 0 了,这和以前的有区别。因为初始化的数组默认是0。
         */
        status[0][0] = 1;
        //开始遍历，先背包，后面物品
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 0; j < packageWeight + 1; j++) {
                status[i][j] = status[i - 1][j];
                if (j >= nums[i - 1])
                    status[i][j] = status[i - 1][j] + status[i - 1][j - nums[i - 1]];
            }
        }
        return status[nums.length][packageWeight];
    }
}
