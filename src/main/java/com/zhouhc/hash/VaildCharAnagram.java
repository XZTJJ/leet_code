package com.zhouhc.hash;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * q242_有效的字母异位词
 * <p>
 * 核心思想 : 异词位指的是字母排序后,两个字符串完全相等,
 */
public class VaildCharAnagram {


    /**
     * 排序后比较
     * 核心思想 : 先排序, 后比较
     * 时间复杂度 : O( nlogn ) , 时间复杂度 : O( n )
     */
    public boolean isAnagramBySort(String s, String t) {
        //非空判断
        if (s == null || t == null || s.length() != t.length())
            return false;
        //toCharArray 底层其实是创建了一个新数组
        char[] sArrays = s.toCharArray();
        Arrays.sort(sArrays);
        char[] tArrays = t.toCharArray();
        Arrays.sort(tArrays);
        return Arrays.equals(sArrays, tArrays);
    }


    /**
     * 哈希方式
     * 核心思想 : 考虑到可能存在uncode字符集，所以直接哈希表，
     * 两者一样证明，所拥有的字符一定是一样的, 因此可以使用
     * Map的方式，如果出现了负数就证明，不一样
     * 时间复杂度 : O( nlogn ) , 时间复杂度 : O( n )
     */
    public boolean isAnagramByMap(String s, String t) {
        //非空判断
        if (s == null || t == null || s.length() != t.length())
            return false;
        //map的方式
        Map<Character, Integer> charCount = new HashMap<Character, Integer>();
        //首先处理singleChar的字符串
        for (char singleChar : s.toCharArray())
            charCount.put(singleChar, charCount.getOrDefault(singleChar, 0) + 1);
        //其次判断是否有负数
        for (char singleChar : t.toCharArray()) {
            charCount.put(singleChar, charCount.getOrDefault(singleChar, 0) - 1);
            if (charCount.get(singleChar) < 0)
                return false;
        }
        return true;
    }


}
