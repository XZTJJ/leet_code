package com.zhouhc.arrays;

/**
 * q59_螺旋矩阵II
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        generateMatrix(3);
        generateMatrixByCircle(3);
    }


    /**
     * 按照旋转方向处理
     * <p>
     * 核心思想  : 根绝旋转方法来确定，可以确定方向始终为 :
     * 右 -> 下 -> 左 -> 上 的顺序，除非到了临界点
     * 或者该位置已经被访问过了，否则需要转方法。难点是
     * 如果确定旋转方向,转方向可以借助 4个二维数组，{1,0}等形式
     * 表示右方向，通过matrix[row][column]的
     * row 和  column 判断是否已经到了临界点了,
     * 因为 row 和 column 考虑到如果超出边界,需要回退
     * 处理起来比较麻烦,因此可以使用 rowBak 和 columnBak 的副本。
     * 必须保证 0 <= rowBak < n &&  0 <= columnBak < n &&  matrix[rowBak][columnBak] 没有访问过
     * <p>
     * 时间复杂度 : O( n ^ 2 )    空间负责度 : O( 1 )
     */
    public static int[][] generateMatrix(int n) {
        //变量
        int[][] matrix = new int[n][n];
        int row = 0, column = 0;
        //旋转方向, 对应 右 , 下 , 左 , 上
        int[][] spiral = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //旋转方向
        int spiralIndex = 0;
        //row 和 column 的变量值
        int rowBak = row, columnBak = column;
        //总数, 临时变量值
        int tempValue = 1, maxValue = n * n;
        //生成过程
        while (tempValue <= maxValue) {
            //赋值
            matrix[row][column] = tempValue;
            //+1
            tempValue++;
            //判断是否越界
            rowBak = row + spiral[spiralIndex][0];
            columnBak = column + spiral[spiralIndex][1];
            //证明要旋转方向了
            if (rowBak >= n || rowBak < 0 || columnBak >= n || columnBak < 0 || matrix[rowBak][columnBak] != 0)
                //保证旋转方向不出界
                spiralIndex = spiralIndex >= spiral.length - 1 ? 0 : spiralIndex + 1;
            //保证不越界
            row += spiral[spiralIndex][0];
            column += spiral[spiralIndex][1];
        }
        //变量的返回
        return matrix;
    }


    /**
     * 按照最外层处理
     * <p>
     * 核心思想 : 从最外层或者圈(上右下左组成，类似洋葱一些剥开)开起来，每一层都是从左上开始顺时针的
     * 因此可以通过这种方式进行，使用其实 startRow 和 startColum ,
     * 最大的 maxRow 和 startColumn 分别处理。
     * <p>
     * 时间负责度 : O ( n ^ 2 ) 空间复杂度 O( 1 )
     */
    public static int[][] generateMatrixByCircle(int n) {
        //开始计算
        int[][] matrix = new int[n][n];
        //层数处理
        int tempValue = 1, maxValue = n * n;
        //row 和 column 最大值
        int maxRow = n - 1, maxColum = n - 1;
        //开始 row 和 column
        int startRow = 0, startColum = 0;
        //循环
        while (startRow <= maxRow && startColum <= maxColum) {
            //处理圈的的上面, 并且 startRow + 1表示已经处理。
            for (int i = startColum; i <= maxColum && tempValue <= maxValue; i++)
                matrix[startRow][i] = tempValue++;
            startRow++;
            //处理圈的右边 , 并且 maxColum - 1 表示处理了
            for (int i = startRow; i <= maxRow && tempValue <= maxValue; i++)
                matrix[i][maxColum] = tempValue++;
            maxColum--;
            //处理圈的下面,并且 maxRow - 1 b表示最处理了
            for (int i = maxColum; i >= startColum && tempValue <= maxValue; i--)
                matrix[maxRow][i] = tempValue++;
            maxRow--;
            //处理圈的左边， 并且 startColumn + 1 表示处理
            for (int i = maxRow; i >= startRow && tempValue <= maxValue; i--)
                matrix[i][startColum] = tempValue++;
            startColum++;
        }
        //返回
        return matrix;
    }

}
