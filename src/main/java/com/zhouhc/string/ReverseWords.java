package com.zhouhc.string;


/**
 * q151_翻转字符串里的单词
 */
public class ReverseWords {

    public static void main(String[] args) {
        System.out.println(reverseWords("  hello  world  "));
//        System.out.println(reverseWords("the sky is blue"));
    }

    /**
     * 核心思想 : 遇到单词直接填入结果集,直到遇到第一个空格符,
     * 将单词移到结果集仅容下该单词长度的末尾的空白部分,
     * 然后再前面补一个空格。 移动完整个单词，需要判断
     * 结果集的首字符是否为空，如果为空，就移除该空格。
     * 时间复杂度 ; O(n) , 空间复杂度 :O(n)
     */
    public static String reverseWords(String s) {
        //总长度
        int size = s.length(), index = size;
        //结果集 和部分索引
        char[] result = new char[size];
        //单词长度
        int wordlength = 0;
        //是否为首个空格
        boolean isFisrtSpace = false;
        //开始循环
        for (int i = 0; i < size; i++) {
            char temp = s.charAt(i);

            //处理第一个空格问题
            if (temp == ' ') {
                //非第一个空格处理，直接跳过
                if (!isFisrtSpace)
                    continue;
                //先移动数组,然后补个空格
                System.arraycopy(result, 0, result, index -= wordlength, wordlength);
                result[--index] = temp;
                //技术要初始化为0,并且标记为要补成0
                wordlength = 0;
                isFisrtSpace = false;
            } else {
                result[wordlength++] = temp;
                isFisrtSpace = true;
            }
        }
        //最后一个单词处理
        if (wordlength > 0) {
            System.arraycopy(result, 0, result, index -= wordlength, wordlength);
        }
        //刚开始那一个元素是不需要有空格的，因此要移除空格
        index += (result[index] == ' ' ? 1 : 0);
        //测试
        return new String(result, index, (size - index));
    }
}
