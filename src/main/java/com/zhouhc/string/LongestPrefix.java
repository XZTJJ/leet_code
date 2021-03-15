package com.zhouhc.string;

/**
 * q14_最长公共前缀
 */
public class LongestPrefix {


    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(findWayOnByOne(strs));
    }

    /**
     *自己的想法 :
     *  通过比较前面两个元素，获取最长前缀，如果存在的话，通过该前缀
     *  一直和数组的其他元素比价，如果发现更短的前缀，就用更短的前缀比较
     *
     *  时间复杂度 : O(n) , 空间复杂度 : O(1)
     *
     *  leetCode还要其他的解法，有兴趣的话可以去看看
     *
     */
    private static String findWayOnByOne(String[] strs) {
        //为空判断
        if (strs == null || strs.length == 0)
            return "";
        int arraysLength = strs.length;
        //处理数组只有一个元素的情况
        if (arraysLength == 1)
            return strs[0];
        //最长前缀的计数
        int longestCount = 0;
        //两个元素长度比较,最短的那个元素
        int tempLength = Math.min(strs[0].length() - 1, strs[1].length() - 1);
        //获取子串长度
        while (longestCount <= tempLength && strs[0].charAt(longestCount) == strs[1].charAt(longestCount))
            longestCount++;
        //证明没有公共前缀
        if (longestCount <= 0)
            return "";
        //获取子串
        String prefix = strs[0].substring(0, longestCount);
        //数组元素逐一比较
        for (int i = 2; i < arraysLength; i++) {
            //如果相同，直接下一个
            if (strs[i].startsWith(prefix))
                continue;
            //从下一个元素开始比较,防止下标越界
            longestCount = Math.min(--longestCount, strs[i].length() - 1);
            //如果不相等的话，找到更短的那个
            while (longestCount >= 0) {
                if (strs[i].charAt(longestCount) == prefix.charAt(longestCount)) {
                    prefix = prefix.substring(0, longestCount + 1);
                    break;
                }
                longestCount--;
            }
            //证明不存在前缀了，直接返回
            if (longestCount < 0)
                return "";
        }

        return prefix;
    }
}
