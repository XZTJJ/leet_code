package com.zhouhc.arrays;

/**
 * q704_二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
//        System.out.println(getTargeByBinarySearch(new int[]{-1, 0, 3, 5, 9, 12}, 2));
        System.out.println(getTargeByBinarySearch(new int[]{-1, 0, 3, 5, 9, 12}, 9));
    }

    /**
     * 时间复杂度 : O(log n)
     * 自己的想法，只要目标索引没有出界，就可以了，注意右边界的范围
     * 也可以算是折半查找
     */
    public static int getTargeByBinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int index = 0;
        while (left <= right) {
            index = (left + right) / 2;
            if (nums[index] == target)
                return index;
            else if (nums[index] < target)
                left = index + 1;
            else
                right = index - 1;
        }
        return -1;
    }
}
