package com.zhouhc.greedy;

/**
 * q45_跳跃游戏 II
 */
public class JumpGameII {

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 1, 1, 4};
//        int[] nums = new int[]{1, 1, 1, 1, 4};
//        int[] nums = new int[]{1, 2, 1, 1, 4};
//        int[] nums = new int[]{1, 2};
        int[] nums = new int[]{0};
        System.out.println(findWayByMax(nums));
    }

    /**
     *  自己的想法，要求最少跳跃次数，只需要每次
     *  跳跃是最大的就可以,只要每次跳的最够远，
     *  那次数就是最小的
     */
    private static int findWayByMax(int[] nums) {
        //跳跃次数,每次跳跃所处的位置,和下次能够达到的最大距离
        int jCount = 0;
        int index = 1;
        int maxStep = nums[0];
        //开始循环
        while (index <= maxStep && index < nums.length) {
            //因为maxStep保存的是下一次的最大距离，如果下一次能到，跳跃次数就得+1
            if (maxStep >= nums.length - 1)
                return jCount + 1;
            //查看本次跳跃所能都达到的最大距离, 获取本次跳跃的最大值，指针需要不断往后走
            int lastMaxStep = maxStep;
            while (index <= lastMaxStep) {
                maxStep = Math.max(index + nums[index], maxStep);
                index++;
            }
            //证明这次跳跃次数不能达到，所以需要加+1
            jCount++;
        }
        //结果返回
        return jCount;
    }

    /**
     * 官方的写法，思路是一样的，每次选最大的条，
     * 不一样的时，利用了区间的思想，只有超过了区间
     * 跳跃次数才需要加1
     */
    private static int findWayByOfficial(int[] nums) {
        int length = nums.length;
        //本次移动区间边界最大值
        int end = 0;
        //本次移动中能达到的最大值
        int maxPosition = 0;
        //跳跃的步数
        int steps = 0;
        //因为end数最大就是length-1, 一旦end为能调到最后，step就不能增加了，因为i == end满足不了条件
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


}
