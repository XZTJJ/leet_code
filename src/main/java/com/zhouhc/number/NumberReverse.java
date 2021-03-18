package com.zhouhc.number;

import org.omg.PortableInterceptor.INACTIVE;

/**
 *q7_整数反转
 * 核心思想 ： 对原来的数进行 10 的 % , / 就行了
 *  棘手的是越界处理,因此可以用临界值缩小对应的倍数后，
 *  进行比较，还需要比较一下，缩小倍数格小数点后的临界值
 *  时间复杂度 : O(1)
 */
public class NumberReverse {
    public static void main(String[] args) {
        int x = 1534236469;
        System.out.println(x + " ---> " + numberReverse(x));
    }

    private static int numberReverse(int x) {
        //进行结果集的判断
        int result = 0;
        //正负数的临界值,以及最大的尾数
        int netNum = Integer.MIN_VALUE / 10;
        final int maxNet = -8;
        int posNum = Integer.MAX_VALUE / 10;
        final int maxPos = 7;
        while (x != 0) {
            int tempValue = x % 10;
            //处理整数溢出的情况
            if (result > posNum || (result == posNum && tempValue > maxPos))
                return 0;
            //处理负数溢出的情况
            if (result < netNum || (result == posNum && tempValue < maxNet))
                return 0;
            //结果相加
            result = result * 10 + tempValue;
            x /= 10;
        }
        //结果返回
        return result;
    }
}
