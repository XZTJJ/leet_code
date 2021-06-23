package com.zhouhc.hash;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * q1002_查找常用字符
 */
public class FindCommonChar {


    //开始处理
    public static void main(String[] args) {

        String[] words = null;
//        words = new String[]{"bella", "label", "roller"};
        words = new String[]{"cool", "lock", "cook"};
        System.out.println(commonChars(words).stream().collect(Collectors.joining(",")));
    }

    /**
     * 双重遍历的思想
     * <p>
     * 核心思想 : 需要申明两组26个字符的数组,
     * 一个用于存储当前字符串中字符出现的次数，一个用于存储该
     * 字符最少出现次数。 每次比较完一个字符串后，都需要重新统计
     * 两个数组
     * <p>
     * 时间复杂度 : O(m (n+x)) m表示字符串数组的长度, n表示字符串得长度, x表示26个字符(也就是)
     * 空间复杂度 : O( 2 * 26)
     */
    public static List<String> commonChars(String[] words) {
        //申明数组
        int[] count = new int[26];
        int[] miniCount = new int[26];
        //数据填充
        Arrays.fill(count, 0);
        Arrays.fill(miniCount, Integer.MAX_VALUE);
        //所有字符串逐一统计
        for (String word : words) {
            //字符串单个统计
            int length = word.length();
            //字符次数统计
            for (int i = 0; i < length; i++)
                count[word.charAt(i) - 'a']++;
            //miniCount保持最小值, count重置为0
            for (int i = 0; i < 26; i++)
                miniCount[i] = Math.min(miniCount[i], count[i]);
            //字符填充
            Arrays.fill(count, 0);
        }
        //结果返回
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < 26; i++) {
            //该元素不存在
            if (miniCount[i] == 0)
                continue;
            //负责添加
            for (int j = 0; j < miniCount[i]; j++)
                result.add(String.valueOf((char) (i + 'a')).intern());
        }
        //结果返回
        return result;
    }
}
