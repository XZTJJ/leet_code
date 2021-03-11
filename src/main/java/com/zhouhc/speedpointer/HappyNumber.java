package com.zhouhc.speedpointer;

import java.util.HashSet;
import java.util.Set;

/**
 * q202_快乐数快乐数
 *      核心思想 : 快乐数的求解过程 就和判断链表中是否存在环一样，
 *  如果一个数不是快乐数，那么一定会存在一个环
 *
 */
public class HappyNumber {


    //测试
    public static void main(String[] args) {
        int num = 19;
        System.out.println("isHappyByHash : " + isHappyByHash(num) + " , isHappyBySpeed : " + isHappyBySpeed(num));
    }

    /**
     *  哈希表的方式
     *  时间复杂度 : O(n) 空间复杂度 : O(1)
     */
    private static boolean isHappyByHash(int num) {
        Set<Integer> elementMap = new HashSet<Integer>();
        //循环比较
        while (num != 1) {
            //数据存在了，不是快乐数
            if (!elementMap.add(num))
                return false;
            num = theNumSum(num);
        }
        //是一个快乐数
        return true;
    }

    /**
     *  快慢指针问题
     *  时间复杂度 : O(n) 空间复杂度 : O(1)
     */
    private static boolean isHappyBySpeed(int num) {
        //定义两个指针
        int fastSpeed = num;
        int lowSpeed = num;
        //判断慢指针是否移动
        boolean isLowSpeedMove = true;
        //开始求解,如果为 1 就证明这是快乐数
        while (fastSpeed != 1) {
            //下一个数
            fastSpeed = theNumSum(fastSpeed);
            //比较,是否在环中相等了
            if (fastSpeed == lowSpeed)
                return false;
            //保持，快指针是慢指针移动速度的两倍
            if (isLowSpeedMove) {
                isLowSpeedMove = false;
            } else {
                isLowSpeedMove = true;
                lowSpeed = theNumSum(lowSpeed);
                //比较,是否在环中相等了
                if (fastSpeed == lowSpeed)
                    return false;
            }
        }
        //证明是一个快乐数
        return true;
    }

    //数字求和
    private static int theNumSum(int target) {
        //求位数之和，
        int sum = 0;
        //位数逐一求和，直接使用 % 和 / 与 10 进行计算就行了
        while (target >= 10) {
            //最末尾的一个数
            int lastNum = target % 10;
            //平法和
            sum += lastNum * lastNum;
            //缩小10倍
            target /= 10;
        }
        //最后一位数相加
        sum += target * target;
        return sum;
    }
}
