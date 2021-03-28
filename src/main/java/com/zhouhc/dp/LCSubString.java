package com.zhouhc.dp;

/**
 * q1143_最长公共子序列
 */
public class LCSubString {

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        System.out.println(findWayByDp(text1,text2));
    }

    /**
     * 某个大佬的解法:
     *   动态规划的思想,使用dp[i][j]表示对应str1在
     *   i处 与 str2 在j处之间,存在相同的元素，
     *   动态规划转换方程式 :
     *    dp[i][j] 分为两种情况:
     *   a. str[i] == str[j]  dp[i][j] = dp[i-1][j-1]+1
     *   b. str[i] == str[j]  dp[i][j] = Max(p[i-1][j], p[i][j-1])
     *  可以通过 abcde 和 ace 这个例子在纸上画一下就知道了
     */
    private static int findWayByDp(String text1, String text2) {
        char[] charText1 = text1.toCharArray();
        char[] charText2 = text2.toCharArray();
        //转态保存,多出一行是为乐代码简洁性考虑
        int[][] status = new int[charText1.length + 1][charText2.length + 1];
        //多一行代码可以直接使用for循环，不用考虑初始化问题
        for (int i = 0; i < charText1.length; i++) {
            //行的遍历
            for (int j = 0; j < charText2.length; j++) {
                //更具方程可以直接判断
                if (charText1[i] == charText2[j])
                    status[i + 1][j + 1] = status[i][j] + 1;
                else
                    //真的是加一行，就非常方便判断了
                    status[i + 1][j + 1] = Math.max(status[i + 1][j], status[i][j + 1]);
            }
        }
        return status[charText1.length][charText2.length];
    }
}
