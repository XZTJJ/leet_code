package com.zhouhc.greedy;

/**
 * q921_使括号有效的最少添加
 */
public class MinParenthesesAdd {

    public static void main(String[] args) {
//        String S = "())";
//        String S = "(((";
//        String S = "()))((";
        String S = "(()(";
        System.out.println(minParent(S));
    }

    /**
     * 自己的想法
     * 1.需要使用left, right ,sum来记录 ( , ) 和需匹配的总数
     * 2.每次，遇到 ( 的时候，需要要 ( 之前所有的 )匹配,也就是将
     *   right清零才行。
     *  贪心算法体现在 : 分析局部最优解
     *
     *  时间复杂度O(n) ,空间复杂度O(n)
     */
    private static int minParent(String S) {
        //记录左括号，右括号，
        int left = 0, right = 0, sum = 0;
        //数组遍历
        char[] strChar = S.toCharArray();
        for (int i = 0; i < strChar.length; i++) {
            //两种情况
            if (strChar[i] == ')') {
                //只是统计右括号的数量，匹配是左括号那里处理
                right++;
            } else {
                /**
                 * 遇到左括号时，只需要判断是否有右括号，如果有右括号
                 * 没有匹配，先把所有的右括号匹配完成。就需要补充括号了
                 */
                sum += right == 0 ? 0 : Math.max(right - left, 0);
                left = Math.max(left - right, 0) + 1;
                right = 0;
            }
        }
        //返回总数，可能存在字符串遍历完了，部分括号没有匹配的情况
        return sum + Math.abs(left - right);
    }

    /**
     * 官方写法 : 思想是一样的，不过太简洁了
     *   使用平衡度进行统计，直接看代码
     *   真简介
     */
    private static int findWayByOfficial(String S) {
        //ans统计总量， bal为平衡系数
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            bal += S.charAt(i) == '(' ? 1 : -1;
            //不过是存在几个) ，一般)数多于(数，bal清零，ans补充少的(括号数。
            if (bal == -1) {
                ans++;
                bal++;
            }
        }

        return ans + bal;
    }

}
