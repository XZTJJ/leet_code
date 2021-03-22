package com.zhouhc.greedy;

import java.util.*;

/**
 * q1326_灌溉花园的最少水龙头数目
 */
public class WaterGarden {

    public static void main(String[] args) {
        //测试
        int n = 3;
//        int n = 5;
//        int n = 3;
//        int[] ranges = new int[]{1, 2, 1, 0, 2, 1, 0, 1};
//        int[] ranges = new int[]{3,4,1,1,0,0};
        int[] ranges = new int[]{0, 0, 0, 0};
        System.out.println(waterGarden(n, ranges));
    }


    /**
     * 官方思想 ： 对于x处能灌溉的返回[li,ri]，用prev(ri) = li,
     *  表示该点所能浇灌的距离，如果存在li = ri的情况，就证明该 li-1
     *  或者 ri+1 与li,ri处不能被浇灌因为只有一个点，不是一个范围，
     *  prev(ri) = li 存在多个li值，为了尽量减少区间个数，应该li
     *  值最少的
     */

    private static int waterGarden(int n, int[] ranges) {
        int[] prev = new int[n + 1];
        Arrays.fill(prev, Integer.MAX_VALUE);
        //标注有边界为r 的最左的边界值li
        for (int i = 0; i <= n; ++i) {
            int l = Math.max(i - ranges[i], 0);
            int r = Math.min(i + ranges[i], n);
            prev[r] = Math.min(prev[r], l);
        }

        //breakpoint表示i处的左边界 ,  furthest表示各个左边界
        int breakpoint = n, furthest = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = n; i > 0; --i) {
            //i处的左边界
            furthest = Math.min(furthest, prev[i]);
            //表示表示上一次的左边界已经达到，需要选取下一个边界值
            if (i == breakpoint) {
                //表示i处的左边界值 和 游标界值相等或者大于，这就导致i处辅警不能被灌溉，直接返回
                if (furthest >= i) {
                    return -1;
                }
                //保存i处最左边的值，也就是最少的x的值
                breakpoint = furthest;
                //因为选择了新的区间，所以区间值+1
                ++ans;
            }
        }

        return ans;
    }

}
