package com.zhouhc.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 * 1. 暴力求解
 * 2. 哈希求解
 */
public class FirstNoExistChar {

    public static void main(String[] args) {
        String str = "loveleetcode";
        System.out.println("给定字符串为: " + str + " , 找到第一个不重复字符的下标索引为: \t"
                + forceSolvtion(str) + "\t" + hashSolvtion(str));
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
}
