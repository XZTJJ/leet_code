package com.zhouhc.link;

import com.zhouhc.link.comm.ListNode;

/**
 * q445_两数相加II
 */
public class Add2NumsII {

    public static void main(String[] args) {
        //制造数据

        ListNode l1 = getListData(new int[]{7, 2, 4, 3});
        ListNode l2 = getListData(new int[]{5, 6, 4});
        //真实的求解过程
        solution(l1, l2);
    }

    /**
     * 遍历求解
     * 思路 : 首先尾排序两个表，保证是从个位数相加的
     *      ,遍历两个链表，为了保持有序，使用尾插法
     *      10进制存在进一位的思想，额外需要一个int变量
     *      保存是否需要进位，默认为0。
     * 时间复杂度 : O(max(n,m)) ,ps : n,m为两个链表的长度
     */

    private static ListNode solution(ListNode l1, ListNode l2) {
        //对2个链表进行尾排序
        ListNode l1Tail = null;
        while (l1 != null) {
            l1Tail = new ListNode(l1.val,l1Tail);
            l1 = l1.next;
        }
        ListNode l2Tail = null;
        while (l2 != null) {
            l2Tail = new ListNode(l2.val,l2Tail);
            l2 = l2.next;
        }

        //设置头节点
        ListNode headPoint = null;
        //是否需要进位
        int carry = 0;
        //确保遍历完所有的数组
        while (l1Tail != null || l2Tail != null) {
            //获取末尾数之和
            int data = (l1Tail != null ? l1Tail.val : 0) + (l2Tail != null ? l2Tail.val : 0) + carry;
            //创建节点，修改头指针域，保持头指针始终指向刚插入的那个
            headPoint = new ListNode(data % 10,headPoint);
            //每次都要修改carry值
            carry = data / 10;
            //让l1 和 l2 指向下一个元素
            if (l1Tail != null)
                l1Tail = l1Tail.next;
            if (l2Tail != null)
                l2Tail = l2Tail.next;
        }
        //处理进位的操作
        if (carry != 0) {
            headPoint = new ListNode(carry,headPoint);
            carry = 0;
        }
        //headPoint是头指针
        return headPoint;
    }

    /**
     * 数据拼接
     */
    private static ListNode getListData(int[] nums) {
        //设置头指针,尾指针，头指针的作用为左右节点操作一直，尾指针是为了保持有序
        ListNode headPoint, tailPoint;
        //初始化头尾节点
        headPoint = tailPoint = new ListNode(0,null);
        for (int num : nums) {
            //创建节点，修改尾指针域，保持尾指针始终指向最后一个元素
            tailPoint.next = new ListNode(num,null);
            tailPoint = tailPoint.next;
            //记录链表长度
            headPoint.val = headPoint.val + 1;
        }
        return headPoint.next;
    }
}
