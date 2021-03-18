package com.zhouhc.greedy;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * q402_移掉K位数字
 */
public class RemoveKDigits {

    public static void main(String[] args) {
//        String num = "103";
        String num = "1432219";
//        String num = "10200";
//        int k = 1;
//        int k = 1;
        int k = 3;
        System.out.println(findWayByMin(num, k));
    }


    /**
     * 自己想法 :
     *    高位顺序移除元素，只要每加入一个元素 和 原来的
     *    元素比较大小，始终取最小值就可以了,遇到0直接跳过
     *    就好，并且需要出掉0
     *
     *官方的思想 :  1bxx > 1axx 存在 b > a , 只要维持从左到右是
     *    单调递增，就可以使得整个数组是最大的，如果整个序列本来
     *    就是单调递增的(和栈顶元素相等也入栈),那么直接以后后面的元素就是最大的。
     *    使用一个数组来模拟队列，因为Java自动拆装包太慢了
     *
     *    时间复杂度 : O(n) 空间复杂度 : O(n)
     */
    private static String findWayByMin(String num, int k) {
        //移除所有元素的情况
        int length = num.length();
        //初始化双端队列的元素 , 始终保持一个单调递增的栈
        char[] deque = new char[length];
        //初始化数组的索引下标
        int dIndex = 0;
        //index为索引下标
        int index = 0, delCount = 0;
        //查看是否需要删除,并且全部的元素已经入队列了
        while (index < length) {
            char c = num.charAt(index);
            //队列为空，或者已经移除了对应多的元素后，直接入队列
            if (dIndex == 0 || delCount >= k)
                deque[dIndex++] = c;
            else {
                //如果尾部的元素不小于外边的元素，尾部元素直接出队列
                while (dIndex != 0 && deque[dIndex - 1] > c && delCount < k) {
                    //移除元素，并且增加删除数
                    dIndex--;
                    delCount++;
                }
                //当前元素入队列
                deque[dIndex++] = c;
            }
            //索引值+1
            index++;
        }
        //移除指定要求的元素
        for (; delCount < k; delCount++)
            dIndex--;
        //拼接数据
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dIndex; i++) {
            Character c = deque[i];
            if (c == '0' && stringBuilder.length() == 0)
                continue;
            stringBuilder.append(c);
        }
        //空判断
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }

}
