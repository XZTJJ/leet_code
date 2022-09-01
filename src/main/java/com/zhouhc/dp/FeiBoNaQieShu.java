package com.zhouhc.dp;

/**
 * dp 简单 状态方程式为 : f(n) = f(n-1) + f(n-2)
 */
public class FeiBoNaQieShu {

    public static void main(String[] args) {
        System.out.println(fib(4));
    }

    /**
     * dp, 状态方程式为 : f(n) = f(n-1) + f(n-2)
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n < 2)
            return n;
        int[] status = new int[n+1];
        status[0] = 0;
        status[1] = 1;
        for (int i = 2; i <= n; i++) {
            status[i] = status[i - 1] + status[i - 2];
        }
        return status[n];
    }
}
