package com.zhouhc.greedy;

import java.util.Arrays;

/**
 * q455_分发饼干
 */
public class GiveCookies {


    public static void main(String[] args) {
        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};
        System.out.println(findWayBYSort(g, s));
    }

    /**
     * 核心思想 : 排序+贪心，
     *  排序的原因是降低时间复杂度，贪心是必须对孩子
     *  要大于等于胃口的饼干量, 感觉贪心思想不是很严重,
     *  这个局部最优解，有些太简单了...
     *  时间复杂度 : O(n)[n 为孩子数或者饼干书的最大值]   空间复杂度: O(1)
     */
    private static int findWayBYSort(int[] g, int[] s) {
        if (g == null || s == null)
            return 0;
        //饼干,孩子排序
        Arrays.sort(g);
        Arrays.sort(s);
        //获取饼干或者孩子的索引值
        int gIndex = 0, sIndex = 0;
        //结果
        int result = 0;
        //循环匹配
        while (gIndex < g.length && sIndex < s.length) {
            //证明这个饼干比孩子胃口小，不能被满足直接跳过
            if (s[sIndex++] < g[gIndex])
                continue;
            //处理能满足的情况,换成另一个孩子，并且总数加1
            gIndex++;
            result++;
        }

        //能够分配的最大数
        return result;
    }
}
