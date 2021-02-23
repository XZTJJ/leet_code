package com.zhouhc.slidingWindow;

/**
 * q11_盛最多水的容器
 * 核心思想 : 双指针问题
 */
public class MaxContainer {


    public static void main(String[] args) {
        System.out.println(maxContainerSolution(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    /**
     * 核心思想 : 双指针方式，A指向头，B指向尾，谁的数值小，谁移动，
     *           因为 x 不变，决定体积的是 y 的最小值， 具体求证
     *           看官方
     */
    public static int maxContainerSolution(int[] height) {
        //空判断
        if (height == null || height.length == 0)
            return 0;
        //双指针,体积初始化
        int start = 0, end = height.length - 1, container = 0;
        //循环
        while (start < end) {
            //获取最大值
            container = Math.max(container, (end - start) * Math.min(height[start], height[end]));
            //移动最小的那个指针
            if (height[start] < height[end])
                start++;
            else
                end--;
        }
        return container;
    }
}
