package com.zhouhc.string;

/**
 * q344_反转字符串
 */
public class ReverseString {


    /**
     * 核心思想 ：普通的反转思想就好
     * <p>
     * 时间复杂度 : O (n) ,空间复杂度 :O (1)
     */
    public static void reverseString(char[] s) {
        int length = s.length - 1;
        for (int i = 0; i <= length / 2; i++) {
            char temp = s[i];
            s[i] = s[length - i];
            s[length - i] = temp;
        }
    }
}
