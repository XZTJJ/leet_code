package com.zhouhc.mergeinterval;


import java.util.*;

/**
 * q56_合并区间
 * 难点 : 因为给定的区间中的区间不是有序的
 *        所以不能直接排序，所以徐亚先先排序
 *        然后在遍历 和并
 */
public class IntervalMerge {


    public static void main(String[] args) {
        int[][] intervals = new int[][]{new int[]{1, 4}, new int[]{0, 2}, new int[]{3, 5}};
        mergeInterval(intervals);
    }

    /**
     *     通过快速排序的方式合并区间
     *    时间复杂度 ： O(n log n)   空间复杂度 : O(n)
     */
    private static int[][] mergeInterval(int[][] intervals) {
        //空判断
        if (intervals == null || intervals.length == 0)
            return null;
        //记录新增加的元素
        int mergeTotal = 0;
        int[][] tempMerge = new int[intervals.length][2];
        //先对给定的区间排序，使用快速排序的方式
        quickSort(intervals);
        //使用jdk自带的排序算法
        /*
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });*/
        /**
         *      现在区间是按照左端口排序的升序排序了，存在三种情况，
         * 完全重叠，不符重叠，没有重叠
         */
        tempMerge[0] = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            //前一个元素,已经放在不包含的区间中了
            int[] preInterval = tempMerge[mergeTotal];
            int[] currentval = intervals[i];
            //重叠的情况，
            if (preInterval[1] >= currentval[0]) {
                //部分重叠的情况，需要修改 preInterval 的右边的值
                preInterval[1] = Math.max(preInterval[1], currentval[1]);
            } else {
                //没有重叠的情况
                tempMerge[++mergeTotal] = currentval;
            }

        }
        //数组的赋值，去掉不需要的元素
        int[][] reuslt = new int[mergeTotal + 1][2];
        for (int i = 0; i <= mergeTotal; i++)
            reuslt[i] = tempMerge[i];
        return reuslt;
    }


    /**
     *   好久没有写快速排序算法了，手写一下快速排序算法, 使用循环的方式
     * 使用递归版本的方式可读性更好，但是递归版本的性能没有循环版本好
     */
    private static void quickSort(int[][] intervals) {
        //继续需要排序的区间问题
        Queue<int[]> manyIntervals = new ArrayDeque<int[]>();
        //入队列
        manyIntervals.offer(new int[]{0, intervals.length - 1});
        //确保已经排序了所有的区间了
        while (manyIntervals.size() > 0) {
            //需要带排序的区间以及边界值
            int[] needSortIntervals = manyIntervals.remove();
            int left = needSortIntervals[0];
            int right = needSortIntervals[1];
            //相等，直接跳过
            if (left >= right)
                continue;
            //选取中间元素的下标 和 备份的值
            int middle = (left + right) / 2;
            int[] middleValueBak = intervals[middle];
            //开始排序 , 先从右边元素开始找，然后是左边元素
            while (left <= right) {
                //先从右边找到比目标值小的元素，并且元素不能越界
                if (right > middle) {
                    if (intervals[right][0] < middleValueBak[0]) {
                        //修改元素，并且修改标志，交换数据的下标
                        intervals[middle] = intervals[right];
                        middle = right;
                    }
                    right--;
                } else {
                    //然后从左边找到一个比目标值大的元素，并且元素不能越界
                    if (intervals[left][0] > middleValueBak[0]) {
                        //修改元素，并且修改标志，交换数据的下标
                        intervals[middle] = intervals[left];
                        middle = left;
                    }
                    left++;
                }
            }
            //证明元素已经排序好，并且，left == right了,继续下面需要排序的两个区间
            intervals[middle] = middleValueBak;
            manyIntervals.offer(new int[]{needSortIntervals[0], middle - 1});
            manyIntervals.offer(new int[]{middle + 1, needSortIntervals[1]});
        }
    }
}
