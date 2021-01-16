package com.zhouhc.link;

import com.zhouhc.link.comm.ListNode;

import java.util.Stack;

/**
 * q19_删除链表的倒数第N个节点
 *  1.利用栈的方式，时间复杂度 : O(n), 空间复杂度 : O(n)
 *  2.利用双节点的方式, 时间复杂度 : O(n) ,空间复杂度 : O(n)
 */
public class DelRecLinkNode {

    public static void main(String[] args) {
        ListNode l1stack = getListData(new int[]{1, 2, 3, 4, 5});
        stackSolution(l1stack, 2);
        ListNode l1double = getListData(new int[]{1});
        doubleNodeSolution(l1double, 1);
    }

    /**
     * 1.利用栈的方式
     * 思路 : 主要是依靠栈的后进先出, 栈顶的
     *       n 对应 链表的倒数 n 。
     * 时间复杂度 : O(n) , 空间复杂度 : O(n)
     */
    private static ListNode stackSolution(ListNode l1, int target) {
        //声明栈，并且将原来的元素入栈
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode head = l1;
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        //找到要删除元素的前一个
        for (int i = 0; i < (target + 1); i++)
            head = stack.pop();
        //删除元素,并且返回对应的头结点
        if (head != null && head.next != null)
            head.next = head.next.next;
        return l1;
    }

    /**
     * 2.利用双节点的方式
     * 思路 : 利用双节点A,B的方式，两个节点相差 n 个元素
     *       A在链表尾部的时候，B刚好在要删除的元素的前一个
     * 时间复杂度 : O(n) 空间复杂度 : O(1)
     */
    private static ListNode doubleNodeSolution(ListNode l1, int target) {
        //声明双节点，并且初始化为头结点
        ListNode firstNode, secondNode, headPonit;
        firstNode = secondNode = headPonit = new ListNode(0, l1);
        //始终保持 firstNode , secondNode相差n个元素
        for (int i = 0; i < (target + 1); i++)
            firstNode = firstNode.next;
        while (firstNode != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        //删除逻辑
        if (secondNode != null && secondNode.next != null)
            secondNode.next = secondNode.next.next;

        return headPonit.next;
    }

    /**
     * 数据拼接
     */
    private static ListNode getListData(int[] nums) {
        //设置头指针,尾指针，头指针的作用为左右节点操作一直，尾指针是为了保持有序
        ListNode headPoint, tailPoint;
        //初始化头尾节点
        headPoint = tailPoint = new ListNode(0, null);
        for (int num : nums) {
            //创建节点，修改尾指针域，保持尾指针始终指向最后一个元素
            tailPoint.next = new ListNode(num, null);
            tailPoint = tailPoint.next;
            //记录链表长度
            headPoint.val = headPoint.val + 1;
        }
        return headPoint.next;
    }
}
