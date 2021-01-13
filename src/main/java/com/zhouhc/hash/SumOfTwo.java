package com.zhouhc.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * <p>
 * 1. 暴力枚举
 * 2. 哈希求解
 */
public class SumOfTwo {
    public static void main(String[] args) {
        //测试
        int[] nums = new int[]{3, 3};
        System.out.println(Arrays.toString(forceEmun(nums, 6)));
        System.out.println(Arrays.toString(hash(nums, 6)));
    }


    /**
     * 暴力枚举
     * 思路: 枚举数组中的任意一个数 x ，在数组中寻找 target - x
     *      返回对应两个数的下标和。
     * 时间复杂度: O(n^2)
     */
    private static int[] forceEmun(int[] nums, int target) {
        int[] result = null;
        //枚举数组中的所有数
        for (int i = 0; i < nums.length; i++) {
            //寻找是否存在 target - x 的值, 下标从 i + 1 开始是因为
            // 下标为 1 ... i 已经被前面匹配了，所以不需要再次匹配了
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    result = new int[]{i, j};
            }
        }
        return result;
    }

    /***
     * 哈希求解
     * 思路 : 针对暴力求解，最大的缺陷是 花费在 target - x 的时间太多了
     *       如果能O(1)级别找到 target - x 将会减少很多时间，因此可以
     *       用哈希的方式。
     * 时间复杂度 : O(n)
     */
    private static int[] hash(int[] nums, int target) {
        int[] result = null;
        //定义哈希表
        Map<Integer, Integer> hashCode = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            // 查看是否存在 target -x 的数值，存在就可以返回了
            Integer integer = hashCode.get(target - nums[i]);
            if (integer != null)
                return result = new int[]{i, integer};
            hashCode.put(nums[i], i);
        }
        return result;
    }
}
