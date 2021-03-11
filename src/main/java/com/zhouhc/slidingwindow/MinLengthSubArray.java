package com.zhouhc.slidingwindow;

/**
 *  q209_长度最小的子数组
 *
 */
public class MinLengthSubArray {

    public static void main(String[] args) {
        int target1 = 7;
        int[] nums1 = new int[]{2, 3, 1, 2, 4, 3};
        int target2 = 4;
        int[] nums2 = new int[]{1, 4, 4};
        int target3 = 11;
        int[] nums3 = new int[]{1, 1, 1, 1, 1, 1, 1, 1};

        System.out.println("mySleft : target : " + target1 + " , minLength : " + mySleft(target1, nums1) +
                " target : " + target2 + " , minLength : " + mySleft(target2, nums2) +
                " target : " + target3 + " , minLength : " + mySleft(target3, nums3));
    }

    /**
     * 自己的想法 : 利用双指针, 左指针指子数组最左边的下标
     *  右指针指向连续子数组的临界值, 通过和 已经存在的连续子数组
     *  的最小值比较，记录长度最小的数组
     *  时间复杂度 : O(n) 空间复杂度 : O(1)
     */
    private static int mySleft(int target, int[] nums) {
        //长度和非空判断
        if (nums == null || nums.length == 0)
            return 0;
        //声明最有指针
        int left = 0, right = 0;
        //临时存储总大小
        int tempValue = 0, length = Integer.MAX_VALUE;
        //循环判断
        while (right < nums.length) {
            //数值相加
            tempValue += nums[right];
            //刚好出现在临界值的情况
            while (tempValue >= target) {
                //首先获取连续子数组的最小长度
                length = Math.min(right - left + 1, length);
                //
                tempValue -= nums[left++];
            }
            //右边的值一直累加
            right++;
        }

        //返回数值
        return length == Integer.MAX_VALUE ? 0 : length;
    }
}
