package com.zhouhc.hash;


import java.util.HashSet;
import java.util.Set;

/**
 * q349_两个数组的交集
 */
public class ArrayIntersection {

    /**
     * hashMap的方式
     * <p>
     * 时间复杂度 :O(n)
     * 空间复杂度 : O(n)
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null)
            return null;
        //set一下
        Set<Integer> num1Set = new HashSet<Integer>();
        for (int num : nums1)
            num1Set.add(num);
        //保存数据
        Set<Integer> num2Set = new HashSet<Integer>();
        for (int num : nums2)
            num2Set.add(num);
        //返回数组
        Set<Integer> result = new HashSet<Integer>();
        for (int num : num1Set) {
            if (num2Set.contains(num))
                result.add(num);
        }
        //结果集
        int[] resultNum = new int[result.size()];
        int index = 0;
        for (int num : result)
            resultNum[index++] = num;
        return resultNum;
    }
}
