package com.zhouhc.arrays;

/**
 * q27_移除元素
 */
public class RemoveElement {
    public static void main(String[] args) {
//        System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));
//        System.out.println(removeElement(new int[]{3, 3, 3, 3}, 3));
//        System.out.println(removeElement(new int[]{3, 3, 3, 3}, 2));
        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
    }
    /**
     * 双指针的思想 :
     * 一个用于记录新数组的索引位置，一个用于记录旧数组的索引位置
     * 时间复杂度 : O(n)  空间复杂度 : O(1)
     */
    public static int removeElement(int[] nums, int val) {
        int newIndex = 0, oldIndex = 0, tempValue;
        //遍历旧数组
        while (oldIndex < nums.length) {
            tempValue = nums[oldIndex++];
            if (tempValue == val)
                continue;
            nums[newIndex++] = tempValue;
        }
        return newIndex;
    }
}
