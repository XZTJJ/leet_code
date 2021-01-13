package com.zhouhc.prime;

/**
 * 计数质数
 * 1.暴力求解
 * 2.埃氏筛
 */
public class PrimeCount {

    public static void main(String[] args) {
        int[] targetNums = new int[]{0,1,10,20,50};
        for (int target : targetNums)
            System.out.println(target+"以内存在质数个数为:"+"\t"+
                    forceCount(target)+"\t"+eratosthenesCOunt(target));
    }

    /**
     * 暴力求解
     * 思路 ： 2~n 每个数都进行判断
     * 时间复杂度 : O(n)
     */
    private static int forceCount(int target) {
        //标识该数是不是质数
        boolean isPrime = true;
        //质数总量统计
        int count = 0;
        //对每个数都进行判断是否为质数
        for (int i = 2; i < target; i++) {
            //赋值操作
            isPrime = true;
            //能否被 2~i-1 的任一整数整除
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
                count++;
        }
        return count;
    }


    /**
     * 埃氏筛：
     * 1.任意一个数y而言, 两个因数最小差为0( x^2 = y,两个因数相等)，
     * 假设 x^2 = y，只需要比较小于 x 的因数，大于 x 的因数必定是被
     * 小于 x 的因素给比较了，可以直接跳过了。
     * 2.对于质数 y ,那么 2y,3y..一定不是质数，因为存在因数y, 因此可以直接
     * 标记出来. 对于 j >>> 2 的情况，2j,3j... 一定被远小于 j 的 2,3..等
     * 标记过了，因此可以直接从 j^2 开始。
     *
     * 时间复杂度: O(n log log n) ps:不知道怎么证明的
     */

    private static int eratosthenesCOunt(int target) {
        //用于标记数组，用于标记对应数字是否为质数，并且默认所有数都为质数
        boolean[] markType = new boolean[target];
        for (int i = 0; i < markType.length; i++) {
            markType[i] = true;
        }
        //统计结果
        int count = 0;
        //2 ~ n 每个数都要判断
        for (int i = 2; i < target; i++) {
            // 证明这个数为非质数，直接跳过
            if (!markType[i])
                continue;
            //证明该数为质数
            count++;
            //根据 方法上注解1 可以知道，对于比i大的因数，绝对被比小于 i 的因数判断过，因此是不需要重复判断的
            //比如 12 = 2 * 6 ，6就可以省略判断，因为2已经判断过了
            if (i * i < target) {
                // 对于质数j而言，2j,3j,4j...绝对不是质数，因为存在因数 j
                // 从 j^2 开始判断的依据是: j >=2 所以, 2~j 之间的数值，已经被2,3,4...j标记过了，
                for (int j = i * i; j < target; j += i) {
                    markType[j] = false;
                }
            }
        }

        return count;
    }
}
