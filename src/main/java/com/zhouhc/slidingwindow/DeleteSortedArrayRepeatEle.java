package com.zhouhc.slidingwindow;

/**
 * q26_删除排序数组中的重复项
 *
 */
public class DeleteSortedArrayRepeatEle {

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,2};
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println("新数组长度 : " + removeDuplicates(nums));
    }


    /**
     * 自己的方式实现删除有序数组的长度
     * 核心思想 : 只需要两个指针就好，一个保留遍历，
     *   一个用于记录新数组的长度 ， 时间复杂度O(n)
     */
    private static int removeDuplicates(int[] nums) {
        int index = 0, nextIndex = 1;
        if (nums == null || nums.length == 0)
            return index;
        //开始循环删除
        while (nextIndex < nums.length) {
            //比较要添加元素是否和新数组的最后一个元素相同，相同直接跳过
            if (nums[nextIndex] == nums[index])
                ++nextIndex;
                //如果元素不相等,把不相等的元素添加到新数组最后一个元素，同时原来数组也要后移
            else
                nums[++index] = nums[nextIndex++];

        }
        //返回数组的长度
        return index + 1;
    }
}
