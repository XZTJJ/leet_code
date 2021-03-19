package com.zhouhc.greedy;

/**
 * q55_跳跃游戏
 */
public class JumpGame {

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 1, 1, 4};
        int[] nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(findWayByMaxStep(nums));
    }

    /**
     * 自己的一些想法：index + step 始终保持最大值
     *
     *  index不能整个遍历，通过给定的值计算出来
     *  时间复杂度 :O(n)  空间复杂度 : O(1)
     */
    private static boolean findWayByMaxStep(int[] nums) {
        //初始化的数组的下标
        int index = 0;
        //总共能走的步数
        int maxStep = nums[0] + index;
        //index移动的返回始终不能超过maxStep
        while (index < maxStep) {
            //如果第一步就能达到，直接跳过
            if (maxStep >= nums.length - 1)
                break;
            index++;
            //计算下一个值
            maxStep = Math.max(maxStep, index + nums[index]);
        }

        return maxStep >= nums.length - 1 ? true : false;
    }


    /**
     * 官方的写法，非常的简介,
     * 思想是一样的
     */
    private static boolean findWayByOfficial(int[] nums) {
        int n = nums.length;
        //能达到的最大距离
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            //首次只能达到0，后面不能操作 i+nums[i]个距离
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
