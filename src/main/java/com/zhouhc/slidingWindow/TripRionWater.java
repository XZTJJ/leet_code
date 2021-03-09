package com.zhouhc.slidingWindow;

import java.util.Stack;

/**
 * q42_接雨水
 *
 *  大致分为两种解法 ：
 *    1.求 每个元素对应 在 x 轴处能接受的雨水数(记得减去元素本身高度，也就是元素在x处的Y的高度)
 *    然后所有元素接受的雨水数累加。
 *      对应的算法实现有 : 暴力求解 , 动态规划 , 双指针方式
 *    2.利用 B C A (元素x轴对应的高度，满足 A > B > C 形式),就可以利用  B - C 就是形成雨水的高，
 *    宽就是 A -B 的长度。 高 * 宽就是对应该 A,B,C三处能接受的雨水的总数
 *       对应的算法实现有 : 单调递减栈(就是利用B递减方式，逐一求出给定的一片区域的与数量)
 */
public class TripRionWater {


    public static void main(String[] args) {

        int[] height1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = new int[]{4, 2, 0, 3, 2, 5};

        System.out.println("violenceTrap : height1面积为 : " + violenceTrap(height1) + " , height2面积为 : " + violenceTrap(height2));
        System.out.println("dynamicTrap : height1面积为 : " + dynamicTrap(height1) + " , height2面积为 : " + dynamicTrap(height2));
        System.out.println("doublePointer : height1面积为 : " + doublePointer(height1) + " , height2面积为 : " + doublePointer(height2));
        System.out.println("decreaseStacks : height1面积为 : " + decreaseStacks(height1) + " , height2面积为 : " + decreaseStacks(height2));
    }


    /**
     * 暴力求解 :
     * 思路 : 在给定元素 x 处， 往 x 的两边各自寻找 最大的 y 值，分别为
     *    左边Y轴最大值leftMax , 右边Y轴的最大值rightMax，因为 x 处于中间，
     *    所以一定会接受 min(leftMax , rightMax) - HeightX(元素x处本身的高) 的雨水量。
     * 时间复杂度 : O(n^2) , 空间复杂度 : O(1)
     */
    private static int violenceTrap(int[] height) {
        //记录接受雨水的总数
        int total = 0;
        if (height == null || height.length == 0)
            return total;
        //开始分别计算每个元素在x处能接多少水, 最左边和最右边是接不到水的
        for (int index = 1; index < height.length - 1; index++) {
            //最大值
            int leftMax = 0, rightMax = 0;
            //左边的最大值
            for (int leftIndex = index; leftIndex >= 0; leftIndex--)
                leftMax = Math.max(leftMax, height[leftIndex]);
            //右边的最大值
            for (int rightIndex = index; rightIndex < height.length; rightIndex++)
                rightMax = Math.max(rightMax, height[rightIndex]);
            //计算X处的收集的雨水数量
            total += (Math.min(leftMax, rightMax) - height[index]);
        }
        return total;
    }

    /**
     * 动态规划 :
     * 思路 : 针对暴力求解，不要对每个元素都找左右两边的最大值，
     *      一次性提交找出每个元素左边的最大值，右边的最大值，
     *      后面比较的时间可以直接使用了。
     * 时间复杂度 : O(n) , 空间复杂度 : O(n)
     */
    private static int dynamicTrap(int[] height) {
        //雨水总数
        int total = 0;
        //非空判断
        if (height == null || height.length == 0)
            return total;
        //每个元素左边最大值的存储数组
        int[] leftMaxArrays = new int[height.length];
        //设置最左边元素的初始值,以及为每个元素查找最大值
        leftMaxArrays[0] = height[0];
        for (int index = 1; index < height.length; index++)
            leftMaxArrays[index] = Math.max(leftMaxArrays[index - 1], height[index]);
        //同样的道理处理右边的函数
        int[] rightMaxArrays = new int[height.length];
        rightMaxArrays[height.length - 1] = height[height.length - 1];
        for (int index = height.length - 2; index >= 0; index--)
            rightMaxArrays[index] = Math.max(rightMaxArrays[index + 1], height[index]);

        //开始求解，最左边和最右边是不用计算的
        for (int index = 1; index < height.length - 1; index++)
            total += (Math.min(leftMaxArrays[index], rightMaxArrays[index]) - height[index]);
        return total;
    }

    /**
     * 双指针方式 :
     * 思路 : 针对动态规划需要额外遍历遍历两次数组，获取左边最大值数组
     *       , 以及右边最大值数组。
     *           假设元素x的右边一定存在比x的
     *       y轴要大的元素RightX,那么(x,RightX)区间的每个元素所能
     *       收集的最大的雨水量为 RHeightX - RHeightX'(RHeightX' 是
     *       x  到 RightX 之间 y 的数值)。
     *          既然就像求最大面积一样，LeftMax取最左边的值，RightMax
     *      取最右边的值，谁小移动谁。如果Max的值没有更新，就证明当前元素
     *      可以接水，就可以就出该元素所能接水的量，然后累加就好
     *  时间复杂度 : O(n) , 空间复杂度 :O(1)
     */
    private static int doublePointer(int[] height) {
        //雨水总数
        int total = 0;
        //非空判断
        if (height == null || height.length == 0)
            return total;
        //设置左右指针
        int leftIndex = 0, rightIndex = height.length - 1;
        //设置左右最大值
        int leftMax = height[0], rightMax = height[height.length - 1];
        //循环结束的条件
        while (leftIndex < rightIndex) {
            //如果左边比右边大的话，移动右边
            if (height[leftIndex] > height[rightIndex]) {
                //如果大于就更新rightMax的值
                if (height[rightIndex] > rightMax) {
                    rightMax = height[rightIndex];
                } else {
                    //证明存在比 rightMax 小的柱形能接水
                    total += rightMax - height[rightIndex];
                    rightIndex--;
                }
            } else {
                //如果大于就更新rightMax的值
                if (height[leftIndex] > leftMax) {
                    leftMax = height[leftIndex];
                } else {
                    //证明存在比 leftMax 小的柱形能接水
                    total += leftMax - height[leftIndex];
                    leftIndex++;
                }
            }
        }
        return total;
    }

    /**
     * 单调递减栈:
     *   思想 : 就是上面 2 中的说话，不断以 A 替换成 B 来求出所有的
     *   一些列的区域。
     *  时间复杂度 : O(n) , 空间复杂度 : O(n)
     */
    private static int decreaseStacks(int[] height) {
        //雨水总数
        int total = 0;
        //非空判断
        if (height == null || height.length == 0)
            return total;
        //申明单调递减栈，保存的不是数值而是下标。 保存下标的意义是 求宽
        Stack<Integer> indexStack = new Stack<Integer>();
        //遍历求解
        for (int index = 0; index < height.length; index++) {
            /**
             *     栈不为空并且 栈顶元素 比 当前元素 小 , 能接水，
             *  这里的栈顶相当于 C ,栈顶的下一个元素相当于B ，当前元素相当于A
             */
            while (!indexStack.isEmpty() && height[index] > height[indexStack.peek()]) {
                //栈顶下标
                int topIndex = indexStack.pop();
                //栈为空的话，直接出栈
                if (indexStack.isEmpty())
                    break;
                //开始计算宽度
                int width = index - indexStack.peek() - 1;
                //高度 B - A -c
                int high = Math.min(height[indexStack.peek()], height[index]) - height[topIndex];
                total += high * width;
            }
            //其他情况就直接入栈
            indexStack.push(index);
        }

        return total;
    }
}
