package com.zhouhc.dp;


/**
 * q5_最长回文子串
 */
public class LongestSubString {


    public static void main(String[] args) {
        String  s ="babad";
        System.out.println(s + " ---> " +findWayByDp(s));
    }

    /**
     * 动态规划解决问题:
     *   如果一个字符串是回文字符串，那么去掉首位两个字母
     * 那么也绝对是回文字符串。 因此可以得到一个结局
     *  s[i,j] = s[i+1,j-1] && char[i] == char[j]
     *  这个状态决定，因此s[i,j]是否为回文字符串直接判断对应
     *  两个条件就好。不过需要考虑边界情况，如果s[i,j]长度只要
     *  1或者2， 长度为1，必定为回文字符换，长度为2，只需要char[i]
     *   == char[j] 就行.
     *   时间复杂度 : O ( n^2 ) 空间复杂度 : O ( n^2 )
     */
    private static String findWayByDp(String s) {
        //答案
        String ans = "";
        //转成字符数组
        char[] strChars = s.toCharArray();
        int length = s.length();
        //保存转态,动态规划一定会涉及到状态转化，需要保存转态才行
        boolean[][] status = new boolean[length][length];
        //分为两次遍历，一次是长度遍历，一次寻找该长度是否存在回文串的遍历
        for (int l = 0; l < length; l++) {
            //查查看改长度是否存在回文串的问题,
            for (int i = 0; i + l < length; i++) {
                //最右边的值的下标
                int j = i + l;
                //边界值判断,单个字符串绝对是回文字符串
                if (l == 0) {
                    status[i][j] = true;
                } else if (l == 1) {
                    //两个字符，需要相等才能是回文字符串
                    status[i][j] = strChars[i] == strChars[j] ? true : false;
                } else {
                    //超过两个转态的数据就需要判断了
                    status[i][j] = status[i + 1][j - 1] && strChars[i] == strChars[j] ? true : false;
                }

                //判断回文串是否为最大
                if (status[i][j] && l + 1 > ans.length())
                    ans = s.substring(i, j + 1);
            }
        }
        //结果返回
        return ans;
    }
}
