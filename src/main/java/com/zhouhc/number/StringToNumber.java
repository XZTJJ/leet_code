package com.zhouhc.number;

import org.omg.PortableInterceptor.INACTIVE;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * q8_字符串转换整数
 */
public class StringToNumber {

    public static void main(String[] args) {
        //String s = " -42";
        String s = "-91283472332";
        System.out.println(s + "----->" + findNum(s));
        System.out.println(s + "----->" + findNumByAutomata(s));
    }


    /**
     *自己的想法 ： 遍历所有的符号,直接进行对应的运算,
     *      需要注意溢出相关的问题
     * 时间复杂度 : O(n) 空间复杂度 :O(1)
     */
    private static int findNum(String s) {
        //是否负数的标记
        int isNet = 1;
        int result = 0;
        //开始遍历
        char[] chars = s.toCharArray();
        //正负数的临界值,以及最大的尾数
        int netNum = Integer.MIN_VALUE / 10;
        final int maxNet = -8;
        int posNum = Integer.MAX_VALUE / 10;
        final int maxPos = 7;
        //遍历
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //前面的空格直接跳过
            if (c == ' ' && isNet == 1) {
                continue;
            }
            //处理前民正负号问题
            if (c == '-' && isNet == 1) {
                isNet = -1;
                continue;
            }
            if (c == '+' && isNet == 1) {
                isNet = 0;
                continue;
            }
            //处理数字的情况
            if (Character.isDigit(c)) {
                int num = c - '0';
                //处理负数的情况
                if (isNet == -1)
                    num = -num;
                else
                    isNet = 0;
                //处理负数越界的情况
                if (isNet == -1 && (result < netNum || (result == netNum && num < maxNet)))
                    return Integer.MIN_VALUE;
                //处理正数越界的情况
                if (isNet == 0 && (result > posNum || (result == posNum && num > maxPos)))
                    return Integer.MAX_VALUE;
                result = result * 10 + num;
            } else
                //其他情况直接跳出循环
                break;
        }

        //结果返回
        return result;
    }


    /**
     * 官方给的思想
     * 核心思想 : 自动自动转态机思想
     *    通过给定的转态来，来选择对应
     *    的处理流程。
     *    不过需要先确定优先状态机的状态
     * start	初始状态
     * symbol	符号状态
     * number	数字状态
     * end	结束状态
     * 数字和其他字符是引起状态发生变化的原因
     * 	        空格	    -/+	      数字	    其他符号
     * start	start	symbol	number	end
     * symbol	end	    end	    number	end
     * number	end	    end	    number	end
     * end	    end	    end	    end	    end
     *
     *  时间复杂度 : O(n) 空间复杂度 :O(1)
     */
    private static int findNumByAutomata(String s) {
        //有限状态机
        Automata automata = new Automata();
        //状态转换
        int strLength = s.length();
        for (int i = 0; i < strLength; i++) {
            String state = automata.getState(s.charAt(i));
            if (state.equals("end"))
                break;
        }
        return (int) automata.getAns();
    }

    /**
     * 官方用到的自动机的做法,需要用到的内部类
     * 因为这道题主要关注的是自动机的解法，所以
     * 对于溢出这种事情不是很关心
     */
    private static class Automata {
        //状态
        private String state = "start";
        //数值，使用long类型不用担心问题
        private long ans = 0;
        //符号，表示数据的正负，-1表示负，1表示正
        private int flag = 1;
        //状态机的声明,其实使用二维数组，使用基础类型作为状态标识会更快，但是String可读性更加好
        private Map<String, String[]> statusMap = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "symbol", "number", "end"});
            put("symbol", new String[]{"end", "end", "number", "end"});
            put("number", new String[]{"end", "end", "number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};
        //正负最大值
        long posMaxValue = Integer.MAX_VALUE;
        long netMaxValue = Integer.MIN_VALUE;

        //进行状态的转换函数,并且返回状态，这样就可避免一些判断了
        public String getState(char c) {
            //获取下一个状态的下标
            int index = getNextState(c);
            //进行状态的转换
            state = statusMap.get(state)[index];
            //符号和数字转态的处理
            if (state.equals("number")) {
                //数字处理
                ans = ans * 10 + c - '0';
                //越界判断,直接返回了，因为这题不注重溢出问题，所以直接使用long类型了，而不是int类型
                ans = flag == 1 ? Math.min(ans, posMaxValue) : Math.min(ans, -netMaxValue);
                if (ans == posMaxValue || ans == -netMaxValue)
                    state = "end";
            } else if (state.equals("symbol")) {
                //正负数处理
                flag = c == '+' ? 1 : -1;
            }

            //返回对应的状态
            return state;

        }

        //获取结果集
        public long getAns() {
            return flag * ans;
        }

        //获取下一个状态索引
        private int getNextState(char c) {
            //空格索引
            if (c == ' ')
                return 0;
            // -/+ 索引
            if (c == '-' || c == '+')
                return 1;
            //数字索引
            if (Character.isDigit(c))
                return 2;
            else
                return 3;
        }
    }
}
