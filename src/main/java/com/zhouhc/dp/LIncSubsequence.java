package com.zhouhc.dp;

import java.util.Arrays;

/**
 * q300_最长递增子序列
 */
public class LIncSubsequence {

    public static void main(String[] args) {
//        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
//        int[] nums = new int[]{0, 1, 0, 3, 2, 3};
        int[] nums = new int[]{4, 10, 4, 3, 8, 9};
        System.out.println(findWayByDp(nums));
        System.out.println(findWayByGreed(nums));
    }

    /**
     *  官方的想法 :
     *    动态规划
     *    因为数组是无序的，令dp[i]表示到第i个
     * 元素时，最大的递增子序列。对于d[j],0<=j<i
     * 的最大子序列为m ,所以dp[i] 的最大子序列为
     *  m+1,如果num[i] > num[j] ,否者就位m.
     *   转态转换方式为
     *    dp[i] = Max{dp[j]+1 , 存在num[i] > num[j]}
     *  因为数组无序，所以需要遍历0,i才能确定最大值,
     *    需要注意的是 : db[i]表示的在i处，存在多少个
     * 前面小于num[i]的元素。因此dp[i]的数值应该是起伏的
     * , 如果num[i] > num[j] ，db[i] = db[j]+1,
     * 否者证明 j之前没有一个元素是小于num[i]的，这个
     * 时候dp[i] = 1
     */
    private static int findWayByDp(int[] nums) {
        /**
         * 声明保存到i处的最长递增子序列,其实也就是状态转换的结果,
         * 可以是自己本身，所以increaseStatus每个元素至少为1,
         * 可以放到后面初始化,for循环中
         */
        int[] increaseStatus = new int[nums.length];
        //记录最大子子数组长度
        int max = 0;
        //开始遍历数组
        for (int i = 0; i < nums.length; i++) {
            //至少为1，因为可以元素自身
            increaseStatus[i] = 1;
            //遍历o-i 之间最长的子序列长度
            for (int j = 0; j < i; j++) {
                /**
                 * 如果在j处，存在num[i] > num[j]，就说明
                 *  increaseStatus[i] =  increaseStatus[j]+1
                 *  否者  j前面没有一个元素比i小， increaseStatus[i] = 1;
                 *  ， increaseStatus[i]数组不一定是递增的
                 */
                if (nums[i] > nums[j])
                    increaseStatus[i] = Math.max(increaseStatus[i], increaseStatus[j] + 1);
            }
            //比较最大值
            max = Math.max(max, increaseStatus[i]);
        }
        //结果返回
        return max;
    }

    /**
     * 官方思想
     * 贪心算法
     *    使用一个一维数组保存每个递增长度序列的最小值
     * (元素下标+1,len表示目前长度为len的单调递增的序列)
     * 序列的最小值, 只有当准备加入的元素 大于 len处的值
     * len++，并且更新数组len处的值。 如果先加入的元素比较
     * 小，就找到第一个比元素大的值，下标为x， x+1处就是新元素的
     * 位置。
     *
     * 因为是有序序列，所以可以使用二分查找
     */
    private static int findWayByGreed(int[] nums) {
        //声明数组
        int[] minvalue = new int[nums.length];
        //长度
        int length = 0;
        //初始化长度为1的递增序列最小值
        minvalue[length] = nums[0];
        //开始遍历
        for (int i = 1; i < nums.length; i++) {
            //如果比递增序列最后一个元素大，就说明加上该元素，可以构成
            //一个更长的递增序列
            if (nums[i] > minvalue[length])
                minvalue[++length] = nums[i];
                //如果比长度为len的递增序列最后一个元素小，就需要跟新0~len
                //长度的递增序列的末尾元素
            else {
                //二分查找算法,
                int left = 0, right = length;
                int middle = (left + right) / 2;
                //开始查找
                while (left < right) {
                    middle = (left + right) / 2;
                    //如果相等，直接跳出循环
                    if (minvalue[middle] == nums[i]) {
                        left = right = middle;
                        break;
                    }
                    //大于的情况,左边要增加
                    if (minvalue[middle] > nums[i])
                        right = middle - 1;
                    else
                        //小于，右边减少
                        left = middle + 1;
                }
                //如果left比num[i]要大或者相等，证明更新的就是Left，否者更新的就是left+1
                if (minvalue[left] < nums[i])
                    //防止数组越界
                    left = Math.min(left + 1, length);
                minvalue[left] = nums[i];
            }
        }
        return length + 1;
    }
}
