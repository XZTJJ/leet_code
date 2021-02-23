package com.zhouhc.slidingWindow;

import java.util.Arrays;

/**
 * q15_三数之和
 */
public class ThreeNumSum {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 0, 0, 0};
        int[][] datas = tSumSolution(nums);
        System.out.println("");

    }

    /**
     * 三数之和 : 结果不重复，核心思想，先排序就不用排重
     *          这样就可以通过两重循环，唯一确定第三个数，
     *          因为 a + b + c = 0; a不变，当b增大，c必然
     *          减少，才满足条件。所以 b 可以小到大扫描， c 可以
     *          从大到小扫描，这样就能实现 b,c只需要遍历一次。
     *          需要注意的是 a ,b ,c 3层遍历，每层都需要跳过重复元素
     * 时间复杂度 : O(n^2)
     */
    private static int[][] tSumSolution(int[] nums) {
        //返回结果和标记
        int[][] result = new int[3][];
        int index = 0;
        //排序
        Arrays.sort(nums);
        //每层都需要遍历
        for (int first = 0; first < nums.length; first++) {
            //排除重复数据
            if ((first != 0) && (nums[first - 1] == nums[first]))
                continue;
            //标记 c 的查找位置，默认最后一个
            int third = nums.length - 1;
            //第二层循环，2,3层循环不能有交集,否则会重复
            for (int second = first + 1; second < third; second++) {
                //第二层去重
                if ((second != first + 1) && (nums[second - 1] == nums[second]))
                    continue;
                //只处理大于等于0的情况
                while (nums[first] + nums[second] + nums[third] >= 0 && second < third) {
                    //第三层去重
                    if ((third != nums.length - 1) && (nums[third] == nums[third + 1])) {
                        third--;
                        continue;
                    }
                    //处理等于0的情况
                    if (nums[first] + nums[second] + nums[third] == 0 && second < third)
                        result[index++] = new int[]{nums[first], nums[second], nums[third]};
                    third--;
                }
            }
        }

        return result;
    }
}
