package com.zhouhc.greedy;

/**
 * q135_分发糖果
 */
public class GiveCandy {


    public static void main(String[] args) {
//        int[] ratings = new int[]{1, 0, 2};
//        int[] ratings = new int[]{1, 2, 2};
//        int[] ratings = new int[]{2, 2, 2};
//        int[] ratings = new int[]{2};
        int[] ratings = new int[]{1, 3, 2, 2, 1};
        System.out.println(findWayBySequence(ratings));
    }

    /**
     * 自己的想法
     *   对于X1X2X3... , 可以发现，他不是递增序列
     * 就是递减序列(都相等的算是递增序列，不过每次都是从
     * 1开始递增，递增0个元素)， 如果递增序列小于等于递减序列
     * , 为了确保递增最后一个元素分配的数量是正确的，不要讲它归并到
     * 递减序列中，同样，只需要记录递增活递减序列的长度就可以
     * 知道整个序列的长度了
     *
     * leetCode代码就是nb
     * 时间复杂度O(n) ,空间复杂度O(n)
     */
    private static int findWayBySequence(int[] ratings) {
        //对于长度为0或者空的处理
        if (ratings == null || ratings.length == 0)
            return 0;
        //数组的长度
        int n = ratings.length;
        //总数
        int ret = 1;
        /**
         *   分别对应初始分配个数(默认为1)，递增序列的长度(默认序列是递增的
         * 并且递增长度1)，递减序列的长度。 这两个默认为1设置的非常巧妙
         */
        int inc = 1, dec = 0, pre = 1;
        //全部循环
        for (int i = 1; i < n; i++) {
            //对于相等的两个元素也算是递增的，只不过递增长度为1
            if (ratings[i] >= ratings[i - 1]) {
                //每次递增钱都默认初始化递减长度为0
                dec = 0;
                //计算分配的个数,考虑相等的情况
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                //总量,为什么不直接使用inc，还需要pre，因为存在着想的情况，所以不能做直接使用inc操作
                ret += pre;
                //递增的个数,因为存在相等个数，不能直接inc++
                inc = pre;
            } else {
                //递减序列的处理，递减个数+1
                dec++;
                /**
                 * 如果存在递减序列个数和递增序列个数一样，递增序列最后一个元素的数量是要加+1的，
                 * 为了方便操作，直接就在这里，将总量+1, 并且+1的方式非常奇妙
                 */
                if (dec == inc)
                    dec++;
                //因为是存在递减序列，这里的个数值想到与逆着增加，但是结果是一样的
                ret += dec;
                //pre要初始化为1,存在相等的情况
                pre = 1;
            }
        }
        return ret;
    }

}
