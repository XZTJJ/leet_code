package com.zhouhc.slidingwindow;

/**
 * q121_买卖股票的最佳时机
 *
 * 核心思想 : 说指针遍历就好
 */
public class BestTimeSoldStock {


    public static void main(String[] args) {
        int[] prices1 = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices2 = new int[]{7, 6, 4, 3, 1};
        //计算
        System.out.println("mySelfPro : 获取的最大利润 : " + mySelfPro(prices1) + " , 获取的最大利润 : " + mySelfPro(prices2));
        System.out.println("maxProfit : 获取的最大利润 : " + maxProfitPro(prices1) + " , 获取的最大利润 : " + maxProfitPro(prices2));
    }

    /**
     * 自己的想法:  每个节点后面的最大值，然后两两比较就好，就可以得出
     *             最大利润
     * 时间复杂度 : O(n) , 空间复杂度 : (n)
     */
    private static int mySelfPro(int[] prices) {
        //最大利润，以及非空判断
        int total = 0;
        if (prices == null || prices.length == 0)
            return total;
        //最大节点比较
        int[] rightMax = new int[prices.length];
        rightMax[prices.length - 1] = prices[prices.length - 1];
        //存储
        for (int i = prices.length - 2; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], prices[i]);
        //比较计算总额
        for (int i = 0; i < prices.length; i++)
            total = Math.max(rightMax[i] - prices[i], total);
        //总额
        return total;
    }

    /**
     * 穷举的思路 : 假设我们已经在历史最低点买入了，
     *            只需要在历史最高点卖出就可以了,因此
     *            可以使用 minValue记录最小值，然后比较每天的
     *            价格变动，如果比minValue小更新minValue，
     *            否者算收益
     *  时间复杂度 : O(n)， 空间复杂度 : O(1)
     */
    private static int maxProfitPro(int[] prices) {
        //最大利润，以及非空判断
        int total = 0;
        if (prices == null || prices.length == 0)
            return total;
        //声明最小值
        int minValue = prices[0];
        //循环遍历
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue)
                minValue = prices[i];
            else
                total = Math.max(prices[i] - minValue, total);
        }
        return total;
    }

}
