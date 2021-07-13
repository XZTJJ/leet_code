package com.zhouhc.string;

import java.util.Arrays;

/**
 * q28_实现 strStr()
 */
public class startStr {

    public static void main(String[] args) {
//        String haystack = "hello", needle = "ll";
//        String haystack = "aaaaa", needle = "bba";
//        String haystack = "mississippi", needle = "issip";
        String haystack = "aabaaaabaaaabaabaaa", needle = "aabaabaaa";
        System.out.println(strStr(haystack, needle));
    }

    /**
     * KMP算法 : 核心思想就是求出next数组,当数组不匹配的时候
     * 不需要重新匹配，而是通过next跳转特定的位置. next存的是长度，
     * 长度转成索引注意 +/- 1, j算是最大前缀索引
     */
    public static int strStr(String haystack, String needle) {
        if (needle.equals(""))
            return 0;
        //获取next数组
        int[] nextArray = getNextArray(needle);
        //开始匹配
        int hIndex = 0, nIndex = 0;
        while (hIndex < haystack.length() && nIndex < needle.length()) {
            if (haystack.charAt(hIndex) == needle.charAt(nIndex)) {
                hIndex++;
                nIndex++;
            } else if (haystack.charAt(hIndex) != needle.charAt(nIndex) && nIndex == 0) {
                hIndex++;
            } else {
                nIndex = nextArray[nIndex - 1];
            }
        }
        //判断是否匹配
        if (nIndex == needle.length())
            return hIndex - nIndex;
        else
            return -1;
    }


    /**
     * 求next数组,next获取的是最大前后缀个数
     */
    private static int[] getNextArray(String needle) {
        int[] next = new int[needle.length()];
        //最大前缀和最大后缀的索引,j为前缀, i为后缀
        int j = 0, i = 1;
        while (i < next.length) {
            //他们索引大于0的情况
            while (j >= 0) {
                //相等的话，就可以直接 +1;
                if (needle.charAt(i) == needle.charAt(j)) {
                    //索引转成个数要 +1
                    next[i] = j + 1;
                    j += 1;
                    i += 1;
                    break;
                }
                //如果处理nIndex为零 , 并且不相等的情况
                if (j == 0) {
                    i += 1;
                    break;
                }
                //next要变成索引,但是next是长度, next[j-1] 长度转成索引 next[j-1] - 1，
                // 表示子串的最大前缀的索引, 不过要比的是前缀的后继, 所以就变成了了 next[j-1] - 1 + 1
                //变成了 next[j -1]。
                j = next[j - 1] + 1 - 1;
            }
        }
        return next;
    }

}
