package com.zhouhc.string;

import java.util.ArrayList;
import java.util.List;

/**
 * q6_Z字形变换
 */
public class ZConvert {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convertByRowSort(s, numRows));
        System.out.println(convertByRowVisit(s, numRows));
    }

    /**
     * 核心思想 : 可以通过比较字符串长度 和 给定的行数
     *    总共的行数，只有在最上行或者最低行的时候，改变
     *    拼接行数顺序就行。
     * 官方解法，瞬间感觉自己像个傻子一样
     * 时间复杂度 : O(n) : 空间复杂度 : O(10)
     */
    private static String convertByRowSort(String s, int numRows) {
        //如果是1行，直接放回元素组
        if (numRows == 1)
            return s;
        //字符串长度
        int strLength = s.length();
        //行数
        int rows = Math.min(numRows, strLength);
        //声明变量
        List<StringBuilder> strLists = new ArrayList<StringBuilder>();
        for (int i = 0; i < rows; i++)
            strLists.add(new StringBuilder());
        //声明所在行
        int rowNum = 0;
        //标志行是升序还是降序
        boolean isAsc = false;
        //开始按照行顺序评价数据
        for (int i = 0; i < strLength; i++) {
            //获取字符
            char c = s.charAt(i);
            //拼接字符
            strLists.get(rowNum).append(c);
            //如果是最上行或者最下行的，都需要当前行数改变方向
            if (rowNum == 0 || rowNum == (numRows - 1))
                isAsc = !isAsc;
            rowNum += isAsc ? 1 : -1;
        }

        //拼接数据
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder tempBuilder : strLists)
            stringBuilder.append(tempBuilder);
        return stringBuilder.toString();
    }

    /**
     * 自己的一些想法
     * 核心思想 : 可以通过按照行，来检索每一行的所有元素
     *    |     |
     *    |   | |
     *    |  |  |
     *    | |   |
     *    |     |
     *
     *   除了中间斜着的元素外，其他元素，都是间隔一个 Z 字的元素: 想个元素
     * 的数量为 : (numRows -1) * 2. ps : numRows为总行数。
     * 其中斜着的元素也是看成 : 少了一层的 Z 型 字符串。相隔元素的的规律是:
     *  (numRows - rowCur -1) * 2. rowCur为当前行号，除了最上层和
     *  最下层不会存在斜的元素，其他都是存在的。
     *
     *  时间复杂度 : O(n) , 空间复杂度: O(1)
     */
    private static String convertByRowVisit(String s, int numRows) {
        //如果是1行，直接放回元素组
        if (numRows == 1)
            return s;
        //获取元素的长度
        int strLength = s.length();
        //申明当前的行数
        int rowCur = 0;
        //声明变量
        StringBuilder stringBuilder = new StringBuilder();
        //临时索引存放
        int tempIndex = rowCur;
        //不考虑斜着的元素，同一行相邻元素的间隔
        int interval = (numRows - 1) * 2;
        //循环
        while (rowCur < numRows) {
            //证明数组越界了，当前行的元素已经检索完成了
            if (tempIndex >= strLength) {
                tempIndex = ++rowCur;
                continue;
            }
            //获取当前元素.拼接当前元素
            char c = s.charAt(tempIndex);
            stringBuilder.append(c);
            //对于不是最上层或者最下层的行来说，一次要拼接两个元素
            if (rowCur != 0 && rowCur != (numRows - 1)) {
                int others = (numRows - rowCur - 1) * 2 + tempIndex;
                //如果存在这个元素就凭借
                if (others < strLength)
                    stringBuilder.append(s.charAt(others));
            }
            //下一个元素的位置
            tempIndex += interval;
        }
        //结果返回
        return stringBuilder.toString();
    }

}
