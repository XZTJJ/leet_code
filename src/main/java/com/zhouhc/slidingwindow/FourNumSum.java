package com.zhouhc.slidingwindow;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * q18_四数之和
 */
public class FourNumSum {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    /**
     * 核心思想: 先排序, 对外边两层数组进行排序,
     * 并且需要确保每一层不会重复, 对后面的两层就可以
     * 双指针的思想了, 一个指向未开始的地方, 一个从
     * 后面往前推。
     * 时间复杂度 ： O( n ^ 3)  空间复杂度 : O( n log n )
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        //结果声明
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //进行判断
        if (nums == null || nums.length < 4)
            return result;
        //排序
        Arrays.sort(nums);
        //开始遍历
        for (int first = 0; first <= nums.length - 4; first++) {
            //相同元素跳过
            if (first > 0 && nums[first - 1] == nums[first])
                continue;
            //判断是否需要跳出循环
            if (nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target)
                break;
            if (nums[first] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                continue;
            //第二层的遍历
            for (int second = first + 1; second <= nums.length - 3; second++) {
                //相同元素跳过
                if (second > first + 1 && nums[second - 1] == nums[second])
                    continue;
                //同样判断
                if (nums[first] + nums[second] + nums[second + 1] + nums[first + 2] > target)
                    break;
                if (nums[first] + nums[second] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                    continue;
                //第三个或者第四个指针
                int third = second + 1;
                int fourth = nums.length - 1;
                //开始计算
                while (third < fourth) {
                    int sum = nums[first] + nums[second] + nums[third] + nums[fourth];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));
                        //避免重复项
                        while (third < fourth && nums[third] == nums[third + 1])
                            third++;
                        third++;
                        //因为左边增大了，右边就得缩小
                        while (fourth > third && nums[fourth] == nums[fourth - 1])
                            fourth--;
                        fourth--;
                    } else if (sum < target)
                        third++;
                    else
                        fourth--;
                }

            }
        }
        //结果返回
        return result;
    }


}
