package com.zhouhc.string;

/**
 * q541_反转字符串II
 */
public class ReverseStringII {

    public static void main(String[] args) {
//        reverseStr("abcd", 2);
        reverseStr("abcd", 4);
    }


    /**
     * 核心思想 ：长度除以k，看看要反转几次,普通的反转思想就好
     * <p>
     * 时间复杂度 : O (n) ,空间复杂度 :O (1)
     */
    public static String reverseStr(String s, int k) {
        //循环次数
        char[] chars = s.toCharArray();
        int count = (chars.length % k == 0) ? chars.length / k : chars.length / k + 1;
        //开始循环
        for (int i = 0; i < count; i++) {
            //只有偶数才需要反转
            if (i % 2 != 0)
                continue;
            //执行反转,左右指针反转
            int left = i * k;
            //越界判断
            int right = left + k - 1 > chars.length - 1 ? chars.length - 1 : left + k - 1;
            char temp;
            //开始反转
            while (left < right) {
                temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return String.valueOf(chars);
    }
}
