package com.zhouhc.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * q387_字符串中的第一个唯一字符
 * 1. 暴力求解
 * 2. 哈希求解
 * 3. 数组求解(推荐)
 */
public class FirstNoExistChar {

    public static void main(String[] args) {
        String str = "loveleetcode";

        arraySolvtion(str);
    }

    /**
     * 1.暴力求解
     * 思路 : 对每个字符串都进行比较 , 直到找到第一个不重复的字母 x
     * 时间复杂度 : O(n^2)
     */
    private static int forceSolvtion(String str) {
        //标记字段
        boolean isRepeat = false;
        //枚举字符串中所有的字符
        for (int i = 0; i < str.length(); i++) {
            //初始标记和获取字符
            isRepeat = false;
            char c = str.charAt(i);
            // 从 i+1 下标开始寻找，因为 i+1 前面的已经被比较过了。
            for (int j = i + 1; j < str.length(); j++) {
                if (c == str.charAt(j)) {
                    isRepeat = true;
                    break;
                }
            }
            //返回下标
            if (!isRepeat)
                return i;
        }
        return -1;
    }

    /**
     * 1.哈希求解
     * 思路 : 统计所有字母出现的次数，找到次数为1的字符
     * 时间复杂度 : O(n)
     */
    private static int hashSolvtion(String str) {
        //哈希表
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        //统计字母次数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        //找出第一个只出现了一次的字母
        for (int i = 0; i < str.length(); i++) {
            if (charMap.get(str.charAt(i)) == 1)
                return i;
        }

        return -1;
    }

    /**
     *
     * 1. 利用数组的一次寻找的方式 , 比hash快很多(6到7被)，证明Java的
     *    自动装包 和 拆包机制没有的慢。
     *
     */
    private static int arraySolvtion(String str) {
        //哈希表,
        int[] result = new int[256];
        //统计字母次数
        for (int i = 0; i < str.length(); i++) {
            result[str.charAt(i)]++;
        }
        //找出第一个只出现了一次的字母
        for (int i = 0; i < str.length(); i++) {
            if (result[str.charAt(i)] == 1)
                return i;
        }

        return -1;
    }
}
