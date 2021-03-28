package com.zhouhc.dp;

/**
 * q53_最大子序和
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = new int[]{-1};
        System.out.println(findWayByDp(nums));
    }

    /**
     * 同样也是使用动态规划来解
     * 1.因为只需要最大值，而且，转态转化需要依赖
     *  其他转态，为了节省计算,使用O(1)的方式保存数据
     *  时间复杂度 : O(n^2) 空间复杂度 : O(n^2)
     */
    private static int findWayByDp(int[] nums) {
        //最大值
        int maxValue = Integer.MIN_VALUE;
        //直接遍历，i作为下标，l作为长度，遍历完i所有组成的长度，在遍历下一个
        for (int i = 0; i < nums.length; i++) {
            int tempvalue = 0;
            //该长度的序列的最大值
            for (int l = 0; i + l < nums.length; l++) {
                tempvalue = nums[i + l] + tempvalue;
                maxValue = Math.max(tempvalue, maxValue);
            }
        }
        return maxValue;
    }

    /**
     * 官方思想 :
     *    同样可以使用动态规划的思想秋求解，并且时间复杂度
     * 可以控制在O(n)级别，使用f(i)表示已i结果的最大的连续
     * 子串之和，至于是否要将num[i]单独成一段还是将 num[i]
     * 加入到 f(i -1)之中，就需要比较f(i-1)+num[i] ,num[i]
     * 的大小才行
     * 动态规划的状态转换方程式为 :
     *   f(i) = max{f(i-1)+num[i],num[i]}
     *  时间复杂度 :O(n) 空间复杂度 :O(1)
     */
    private static int findWayByOfficial(int[] nums) {
        //f(i)子序列的最大值,整个数组的最大值
        int pre = 0, max = nums[0];
        for (int s : nums) {
            //s是划入前一个序列，或者单独规划成一个序列
            pre = Math.max(pre + s, s);
            //保存最大值
            max = Math.max(pre, max);
        }
        return max;
    }

}
