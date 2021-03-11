package com.zhouhc.slidingwindow;

import java.util.Arrays;

/**
 *   q16_最接近的三数之和
 *   核心思想 : 和三数之和一样，先排序，然后在相加比较
 */

public class CloseToThreeNumSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        System.out.println(mySelfSolution(nums, target));
    }


    //首先先排序，然后在比较 ，这样时间复杂度就可以降低到O(nlgN)的级别
    public static int mySelfSolution(int[] nums, int target) {
        //保存结果
        int closeValue = nums[0] + nums[1] + nums[2];
        //先排序
        Arrays.sort(nums);
        //然后开始两层遍历,最外层的
        for (int firstIndex = 0; firstIndex < nums.length; firstIndex++) {
            if (firstIndex > 0 && nums[firstIndex] == nums[firstIndex - 1])
                continue;
            //为第三层遍历赋值
            int thirdIndex = nums.length - 1;
            //临时值
            int tempValue = 0;
            //第二层遍历
            for (int secondIndex = firstIndex + 1; secondIndex < nums.length; secondIndex++) {
                //通过 第三个数 和差值比较就好 ， 那个更近就是那个
                tempValue = target - nums[firstIndex] - nums[secondIndex];
                //循环求解，直接比较第三个数
                while (thirdIndex > secondIndex) {
                    //如果存在相等直接返回
                    if (nums[thirdIndex] == tempValue)
                        return target;
                    //如果第三个数 要比  tempSum 小，后面的数只会更加的小，没有意义了
                    if (nums[thirdIndex] < tempValue) {
                        //看看是否是最接近的目标值,然后直接跳出循环
                        if ((tempValue - nums[thirdIndex]) < Math.abs(target - closeValue)) {
                            closeValue = nums[firstIndex] + nums[secondIndex] + nums[thirdIndex];
                        }
                        break;
                    }
                    //如果 第三个数比 tempSum 要大 可以直接递减
                    if (nums[thirdIndex] > tempValue) {
                        //看看是否更加接近目标值
                        if ((nums[thirdIndex] - tempValue) < Math.abs(target - closeValue))
                            closeValue = nums[firstIndex] + nums[secondIndex] + nums[thirdIndex];
                    }
                    //递减
                    thirdIndex--;
                }
            }
        }

        return closeValue;
    }


    //官方给的解法,思路是一样的,不过更简洁一些
    public static int toSolution(int[] nums, int target) {
        //保存结果
        int closeValue = nums[0] + nums[1] + nums[2];
        //先排序
        Arrays.sort(nums);
        //然后开始两层遍历,最外层的
        for (int firstIndex = 0; firstIndex < nums.length; firstIndex++) {
            //如果和前一个元素相等，则可以直接跳过该元素
            if (firstIndex > 0 && nums[firstIndex] == nums[firstIndex - 1])
                continue;
            //第二层和第三层的指针
            int secondIndex = firstIndex + 1;
            int thirdIndex = nums.length - 1;
            //三数之和
            int sum = closeValue;
            //循环存在的条件
            while (secondIndex < thirdIndex) {
                //三数之和
                sum = nums[firstIndex] + nums[secondIndex] + nums[thirdIndex];
                //如果相等，则可以直接返回就行了
                if (sum == target)
                    return target;
                if (Math.abs(sum - target) < Math.abs(closeValue - target))
                    closeValue = sum;
                //如果sum 要比target 大，则直接移动  thirdIndex 指针，来减少数值
                if (sum > target) {
                    //一直移动thirdIndex到下一个不同的元素为止
                    int thirdIndexTemp = thirdIndex - 1;
                    while (secondIndex < thirdIndexTemp && nums[thirdIndexTemp] == nums[thirdIndex])
                        thirdIndexTemp--;
                    //为thirdIndex赋值为新的变量
                    thirdIndex = thirdIndexTemp;
                } else {
                    //如果sum 要比target 小，则直接移动  secondIndex 指针，来增加数值
                    int secondIndexTemp = secondIndex + 1;
                    while (secondIndexTemp < thirdIndex && nums[secondIndexTemp] == nums[secondIndex])
                        secondIndexTemp++;
                    //为secondIndex赋值
                    secondIndex = secondIndexTemp;
                }
            }
        }
        return closeValue;
    }
}
