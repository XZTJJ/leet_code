package com.zhouhc.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * q118_杨辉三角
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        int numRows = 7;
        System.out.println(findWayByDP(numRows));

    }

    /**
     * 动态规划
     *  自己的想法，感觉动态规划用于解题有繁琐
     *  时间复杂度 : O(numRow^2)
     */
    private static List<List<Integer>> findWayByDP(int numRows) {
        //声明数组
        List<List<Integer>> resutl = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            Integer[] row = new Integer[i + 1];
            int left = 0, right = i;
            //左右两端为1
            row[left] = row[right] = 1;
            //其他值计算
            while (i > 1 && left < right) {
                left++;
                right--;
                row[left] = row[right] = resutl.get(i - 1).get(left - 1) + resutl.get(i - 1).get(left);

            }
            resutl.add(Arrays.asList(row));
        }
        return resutl;
    }

}
