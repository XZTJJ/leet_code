package com.zhouhc.greedy;

/**
 * q134_加油站
 */
public class GasStation {

    public static void main(String[] args) {
//        int[] gas = new int[]{1, 2, 3, 4, 5};
//        int[] gas = new int[]{2, 3, 4};
        int[] gas = new int[]{5, 8, 2, 8};
//        int[] cost = new int[]{3, 4, 5, 1, 2};
//        int[] cost = new int[]{3, 4, 3};
        int[] cost = new int[]{6, 5, 6, 6};
        System.out.println(findWayByMaxGas(gas, cost));
    }

    /**
     * 自己的想法 : 因为是环形的，只能从一个方向出发，
     *   只要保存 gas[i]+gas[i+1]是最大的，如果如果新
     *   加入的g[i+2]不是最优的，那应该直接舍弃gas[i]，
     *   同时起点只能递增，不可能形成一个环路的，最多就是
     *   可以回到起点，end 追上了start 。
     *
     *   时间复杂度 : O(n) , 空间复杂度 ：O(1)
     */
    private static int findWayByMaxGas(int[] gas, int[] cost) {
        //绝对会走完一次
        for (int startIndex = 0; startIndex < gas.length; startIndex++) {
            //当前加油站是否可以支撑到下一个加油站
            if (gas[startIndex] < cost[startIndex])
                continue;
            //边界条件终止
            int end = (startIndex + 1) % gas.length;
            //汽油的剩余容量
            int leafGas = gas[startIndex] - cost[startIndex];
            //遍历循环的条件, 回到起点或者下一个加油站的油够不够
            while (leafGas >= 0 && end != startIndex) {
                //计算下一个加油站是否可以到达下一个加油站
                leafGas = leafGas + gas[end] - cost[end];
                //end+1 , 注意环
                end = (end + 1) % gas.length;
            }
            //油量足够回到起点
            if (end == startIndex && leafGas >= 0)
                return startIndex;
            else {
                /**
                 * 油量不足回到起点或者，中途某个站点就没有油了,就证明起点选择
                 *   的不对，需要重新更换起点, 如果start > end 表示转过一圈了，但是
                 *   还是不能达到，证明后面的也就不用判断了，路径不可达 X + Y < 0 ,如果
                 *   Y 表示startIndex的处的 gas-cost的量必定大于0，并且只需要到end前一个就好
                 *   end前一个必定小于0 ,  官方可以证明出来: 可以直接以end为启动进行判断就好
                 *    所以下面的 while 循环可以直接用 下面一句话代替。妈的数学太重要了
                 *    startIndex = end > startIndex ? end -1 : gas.length;
                 */
                while (leafGas < 0 && startIndex < end - 1) {
                    //移除掉对应的起点
                    leafGas = leafGas + cost[startIndex] - gas[startIndex];
                    startIndex++;
                }
            }
        }
        return -1;
    }

    /**
     * 官方解法 : 能够证明 从x 为起点加油站，y为不可达的加油站，x < y,存在[x,y)之间的加油站
     *    都不能达到y , 因此可以直接从 y 开始作为起点加油站就好。
     *
     * 果然数学才是王道
     */
    private static int findWayByOfficial(int[] gas, int[] cost) {
        //数量,加油站或者汽车的数量
        int n = gas.length;
        //下标
        int i = 0;
        //循环遍历
        while (i < n) {
            //加油数或者耗油数
            int sumOfGas = 0, sumOfCost = 0;
            //经过的加油站数量
            int cnt = 0;
            while (cnt < n) {
                //i 所能达到的加油站
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                //是否可到
                if (sumOfCost > sumOfGas)
                    break;
                //进过的加油站数加1
                cnt++;
            }
            //如果经过了所有的加油站，直接返回对应的起点
            if (cnt == n)
                return i;
            else
                //证明进过了cnt个加油站后，cnt+1个加油站不可达,直接以cnt+1作为下一个起点
                i = i + cnt + 1;
        }

        return -1;
    }

}
