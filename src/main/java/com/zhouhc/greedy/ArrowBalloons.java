package com.zhouhc.greedy;

import java.util.*;

/**
 * q452_用最少数量的箭引爆气球
 */
public class ArrowBalloons {

    public static void main(String[] args) {
//        int[][] points = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
//        int[][] points = new int[][]{{1,2}, {3,4}, {5,6}, {7,8}};
//        int[][] points = new int[][]{{2, 3}, {2, 3}};
//        int[][] points = new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}};
//        int[][] points = new int[][]{};
//        int[][] points = new int[][]{{10, 16}};
          int[][] points = getTestCase();
        System.out.println(findWayByInterval(points));
    }

    /**
     * 自己的想法 :
     *    1.先排序，减少时间复杂度,
     *    2. ABDF为区间，需要前一个元素和后一个元素比较,用两个区A,B的交集A'B',
     *       和下一个区间D比较，如果 A'B' 和 D
     *      能形成交集 A'B'D'，则可以合并。 否则 D 和下一个区间 F 比较
     *      是否能形成新区间 D'F' ,还是 D 自成一个区间 D'。
     */
    private static int findWayByInterval(int[][] points) {
        //首先排序,左端升序
        /*
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] < o2[0] ? -1 : (o1[0] == o2[0] ? 0 : 1);
            }
        });*/
        quickSort(points);
        //最少需用的箭数
        int arrows = 0;
        //开始循环,遍历所有的元素
        for (int i = 0; i < points.length; i++) {
            //维护区间的交集
            int left = points[i][0];
            int right = points[i][1];
            //下一区间
            int j = i + 1;
            //看看能形成多少交集
            while (j < points.length && points[j][0] <= right) {
                left = Math.max(left, points[j][0]);
                right = Math.min(right, points[j][1]);
                j++;
            }
            //更新下一个要比较的元素,是要形成新交集的
            i = j - 1;
            //箭数要+1
            arrows++;
        }
        //返回数量
        return arrows;
    }


    //获取测试样例
    private static int[][] getTestCase() {
        String caseTest = "[[57747793,81986128],[19885386,69645878],[96516649,186158070],[25202362,75692389],[83368690,85888749],[44897763,112411689],[65180540,105563966],[4089172,7544908],[35005211,56600579],[94702567,121658996],[36465782,97487312],[78722862,112387985],[45174067,113877202],[1513929,3493731],[15634022,51357080],[69133069,95031236],[59961393,148979849],[28175011,84653053],[31176229,84553602],[59484421,74029340],[8413784,65312321],[34575198,108169522],[49798315,88462685],[29566413,114369939],[12776091,37045071],[11759956,61001829],[37806862,80806032],[82906996,118404277],[11702305,96123230],[37477084,64813411],[72660336,131786841],[5750846,38372575],[661313,34587170],[41616124,125970019]]";
        caseTest = caseTest.replace("[", "").replace("]", "");
        String[] strings = caseTest.split(",");
        List<int[]> list = new ArrayList<int[]>();
        //测试
        for (int i = 0; i < strings.length; i = i + 2) {
            list.add(new int[]{Integer.valueOf(strings[i]), Integer.valueOf(strings[i + 1])});
        }
        return list.toArray(new int[][]{});
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
