package com.zhouhc.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * q763_划分字母区间
 *  核心思想: 贪心算法
 */
public class DivideLetterInterval {

    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        System.out.println(Arrays.toString(partitionLabels(S).toArray()));
    }


    /**
     * 官方给的
     * 核心思想: 贪心算法
     * 思路 : 首先遍历整儿字符串，获取整个字符串中每个字符
     *   出现的最后位置，start = end = 0 ，从左往右遍历所有
     *   每加入一个元素的话，获取[start,end]元素的最大的最后
     *   下标 Max , end == Max 就证明这个区间结束了
     */
    private static List<Integer> partitionLabels(String S) {
        //结果集
        List<Integer> result = new ArrayList<Integer>();
        if (S == null || S.equals(""))
            return result;
        //记录每个元素的最后位置
        int[] lastLetterPosition = new int['z' - 'a' + 1];
        //遍历，记录数组元素最后位置
        int strLength = S.length();
        for (int i = 0; i < strLength; i++)
            lastLetterPosition[S.charAt(i) - 'a'] = i;
        //区间下标
        int start = 0, end = 0;
        //区间元素最大的下标值
        int maxPosition = 0;
        //开始计算
        while (end < strLength) {
            //获取区间的最大的 元素的下标值
            char c = S.charAt(end);
            maxPosition = Math.max(maxPosition, lastLetterPosition[c - 'a']);
            //比较区间是否结束
            if (end == maxPosition) {
                //记录区间长度，区间重新复制，最大值归零
                result.add(end - start + 1);
                start = end + 1;
                maxPosition = 0;
            }
            //记录下一个元素
            end++;
        }

        return result;
    }
}
