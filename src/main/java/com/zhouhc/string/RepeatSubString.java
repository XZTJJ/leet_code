package com.zhouhc.string;

import java.util.Arrays;

/**
 * q459_重复的子字符串
 */
public class RepeatSubString {

    public static void main(String[] args) {
        String needle = "aabaabaaa";
        System.out.println(repeatedSubstringPattern(needle));
    }

    /**
     * 利用KMP算法，求出next子串，获取子串的长度。
     * 和字符串长度 取余。
     */
    public static boolean repeatedSubstringPattern(String s) {
        int[] nextArray = getNextArray(s);
        int subStringCount = nextArray.length - nextArray[nextArray.length - 1];
        return nextArray[nextArray.length - 1] != 0 && s.length() % subStringCount == 0;
    }


    /**
     * 求next数组 和 startStr.class的一样
     * 不过这里改成for循环
     */
    private static int[] getNextArray(String needle) {
        int[] next = new int[needle.length()];
        int j = 0;
        //开始匹配
        for (int i = 1; i < next.length; i++) {
            //一直匹配
            while (j >= 0) {
                if (needle.charAt(i) == needle.charAt(j)) {
                    next[i] = j + 1;
                    j++;
                    break;
                }
                if (j == 0)
                    break;
                j = next[j - 1] + 1 - 1;
            }
        }
        return next;
    }
}
