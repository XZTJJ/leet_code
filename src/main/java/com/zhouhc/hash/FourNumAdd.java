package com.zhouhc.hash;


import java.util.HashMap;
import java.util.Map;

/**
 * q454_四数相加 II
 */
public class FourNumAdd {


    /**
     * 核心思想 : 如果正常操作，时间复杂度为O( n ^ 4 ), 需要降低时间复杂度
     * 所以可以考虑使用Map的方式 , 能降低 O( n ^ 3 ) 级别,如果对其中的
     * 某组先进行相加存入Map中，只需要遍历剩余的两组就行。 这样时间复杂度就能
     * 减少到 O(n ^ 2) 级别。
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //声明Map操作
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        int count = 0;
        //开始循环
        for (int num1 : nums1)
            for (int num2 : nums2)
                result.put(num1 + num2, result.getOrDefault(num1 + num2, 0) + 1);
        //进行其余两组的遍历
        for (int num3 : nums3)
            for (int num4 : nums4)
                count += result.getOrDefault(0 - num3 - num4, 0);
        return count;
    }
}
