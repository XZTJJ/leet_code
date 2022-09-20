package com.zhouhc.dp;


/**
 * q96_不同的二叉搜索树
 *
 * f(n)表示以n为根的数的种类。
 * 利用左子树小于根，右子树大于根的特性
 * 左子树 * 右子树的数量就是总的数量。
 * 边界条件，对于只有左子树或者右子树的情况，只是需要+单个子树的量就好了
 * 状态方程式 : f(k) : status[k-1] * status[n-k].
 * 左子树 : status[k-1]，右子树 : status[n-k].
 * 这样就可以递归到子解上面了, status[k]表示
 * 为根的二叉树的数量
 */
public class numTrees {

    public static void main(String[] args) {
        System.out.println(numTrees(2));
    }

    public static int numTrees(int n) {
        //状态
        int[] status = new int[n + 1];
        //边界条件
        for (int i = 1; i <= 2 && i <= n; i++)
            status[i] = i;
        //开始计算
        for (int i = 3; i <= n; i++) {
            //记录总数
            int total = 0;
            for (int j = 1; j <= i; j++) {
                //单子树只需要相加，子树都存在对称的情况，比如1为根的 1...4的子树，和4为根的1...4的子树
                if (j - 1 == 0 || i - j == 0)
                    total += status[j - 1] + status[i - j];
                    //多子树直接相乘
                else
                    total += status[j - 1] * status[i - j];
            }
            status[i] = Math.max(status[i], total);
        }
        return status[n];
    }
}
