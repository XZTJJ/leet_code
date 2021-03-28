package com.zhouhc.dp;

/**
 * q62_不同路径
 */
public class UniquePaths {

    public static void main(String[] args) {
//        int m =3;
//        int n = 7;
//        int m =3;
//        int n = 3;
        int m =3;
        int n = 2;
        System.out.println(findWayByDp(m,n));
    }


    /**
     * 动态规划
     * 自己的想法 :  除非处于最底部或者最右端，不然都是有
     *  两种走法的。 然后就可以总结出规律: 使用f(i,j)表示
     *  在(i，j)这个点能有多少种走法 。 转态转移方法为:
     *  f(i,j) = f(i+1,j)+f(i,j+1) , 因此需要逆推，
     *  从终点逆推到出发点。
     *  时间复杂度 : O(mn), 空间复杂度 ：O(n)
     */
    private static int findWayByDp(int m, int n) {
        //声明X轴的转态转移量
        int[] status = new int[n];
        //从后逆推到出发点，最低端和最右端只有一种走法
        for (int i = m - 2; i >= 0; i--) {
            //每一层的每一格有多少种走法
            for (int j = n - 2; j >= 0; j--) {
                /**
                 * f(i,j) = f(i+1,j)+f(i,j+1) 转移公式，
                 *  通过画图，可以只用一维数组表示，不需要用2
                 *  维数组，节省空间，因为最下层和最后层 的值
                 *  铁定为1，所以可以只用一维数组表示：
                 *     0	x	x	1
                 *     x	x	x	1
                 *     x	x	x	1
                 *     1	1	1	0
                 */
                //f(i,j+1)的值
                int right = status[j] == 0 ? 1 : status[j];
                //f(i+1,j)的值
                int bottom = status[j + 1] == 0 ? 1 : status[j + 1];
                //f(i,j)
                status[j] = right + bottom;

            }
        }
        return status[0] == 0 ? 1 : status[0];
    }

}
