package com.zhouhc.dp;

/**
 * q343_整数拆分
 */
public class IntegerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak(35));
    }


    //状态方程式 : num 拆分成两个数据，n1,n2,
    //如果 f(n1) <= n1, n1不拆分，如果f(n1) > n1,证明
    //可以拆分，直接拆分多个数, 这样就可以递归到子解上面了
    //至于拆成多少个数，又子解的最大值决定
    public static int integerBreak(int n) {
        //为了方便计算，多加一个
        int status[] = new int[n + 1];
        //处理边界条件1,2
        status[1] = status[2] = 1;
        //求解状态，一般是逐一递增的过程
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                int num = i - j;
                //这里不对num进行分解，是因为后面 i 慢慢增长成 num , 所以没有必要分解
                if (status[j] <= j)
                    status[i] = Math.max(j * num, status[i]);
                else
                    status[i] = Math.max(status[j] * num, status[i]);
            }
        }
        return status[n];
    }
}
