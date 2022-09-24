package com.zhouhc.dp;

/**
 * q1049_最后一块石头的重量II
 */
public class LastStoneWeightII {

    public static void main(String[] args) {
        int stone = new LastStoneWeightII().lastStoneWeightII(
                new int[]{31,26,33,21,40}
        );
        System.out.println(stone);
    }

    /**
     * dp思想，和前面的等和子集的解法类型，
     * 这题同样，背包容量被所有的总量的一半，
     * 往背包中放入的最大重量，越是接近一半总量
     * 就证明分隔的两个子集容量越是接近，他们的差值就会
     * 越少
     * 所以动态规划的状态转移方程式:
     * <p>
     * db[i][j] = Math(db[i-1][j],[i-1][j-num[i]]+num[i])  j >= num[i]
     * db[i][j] = db[i-1][j]     j < num[i]
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones)
            sum += stone;
        int half = sum % 2 == 0 ? sum / 2 : sum / 2 + 1;
        //初始化，以及边界条件,当物品为0,背包容量为0时，他们对应的值，就是0;
        int[][] status = new int[stones.length + 1][half + 1];
        for (int i = 0; i < stones.length + 1; i++)
            status[i][0] = 0;
        for (int j = 1; j < half + 1; j++)
            status[0][j] = 0;
        //记录最小值
        int minStone = Integer.MAX_VALUE;
        //先背包，后物品的方式遍历
        for (int i = 1; i < stones.length + 1; i++) {
            for (int j = 1; j < half + 1; j++) {
                //stones的索引是从0开始的，他的索引始终比status的值小1
                status[i][j] = status[i - 1][j];
                if (j >= stones[i - 1])
                    status[i][j] = Math.max(status[i - 1][j], status[i - 1][j - stones[i - 1]] + stones[i - 1]);
                minStone = Math.min(minStone, Math.abs(sum - 2 * status[i][j]));
                //剪枝操作，操作或者等于最大值就可以不用算了
                if (status[i][j] >= half)
                    break;
            }
        }
        return minStone;
    }
}
