package com.zhouhc.slidingWindow;

import java.util.Arrays;

/**
 * q3_无重复字符的最长子串
 *
 */
public class UnRepeatMaxSubStr {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(s + "\n" + solutionMySelf(s));
    }

    /**
     * 自己想的思路
     *    每增加一个字符，都需要和不重复数组中的每个元素比较，如果不重复，增直接
     *    到不重复数组中，如果重复的话，则 旧重复字符串中的元素移到开头，记得
     *    是以不重复数组为标注进行 索引的重置的
     */
    public static int solutionMySelf(String s) {
        if (s == null || s.length() == 0)
            return 0;
        //不重复数组的索引
        int index = 0;
        //记录最大的不重复子串的长度
        int maxLength = 0;
        //不重复索引数组
        char[] charsArrays = new char[20];
        //循环遍历元素
        for (int i = 0; i < s.length(); i++) {
            //是否重复标志
            boolean isRepeat = false;
            //字符
            char theChar = s.charAt(i);
            //游标遍历的方式
            int cursor = 0;
            //判断是否重复
            while (cursor < index) {
                if (charsArrays[cursor] == theChar) {
                    isRepeat = true;
                    break;
                }
                cursor++;
            }
            //如果元素重复
            if (isRepeat) {
                //保存不同子串的最大值
                maxLength = index > maxLength ? index : maxLength;
                //尝试使用JDK字段的工具,比手动for的执行效率要高，不可思议
                charsArrays = Arrays.copyOfRange(charsArrays, cursor + 1, index);
                /**
                 * 因为移动了元素，所以必须重新构建 不重复数组的索引值，求值过程 :
                 *  以不重复数组为标准进行相减， 就index(新重复字符下标) - cursor(旧重复字符下标)
                 *   ， - 1 是为了复用添加元素过程
                 */
                index = index - cursor - 1;
            }
            //不重复元素添加 (对于新的数组而言)
            if(index >= charsArrays.length)
                charsArrays =  Arrays.copyOf(charsArrays, charsArrays.length+10);
            charsArrays[index++] = theChar;
        }

        //最大值比较
        maxLength = index > maxLength ? index : maxLength;
        return maxLength;
    }
}
