package com.zhouhc.link;

import com.zhouhc.link.comm.ListNode;


/**
 * q61_旋转链表
 * 核心思想 ：链表形成环，头节点偏移 size - ( k % size) 位
 *          因为逆向不方便操作，只能正向，但是有可能 k 大于 size ，
 *          处理头节点前一个节点的 next 为 null，斩断环。
 *
 */
public class RotateLink {

    public static void main(String[] args) {
        ListNode listData = getListData(new int[]{1, 2, 3, 4,5});
        ListNode listNode = circleSoluting(listData, 2);
    }

    /**
     * 思路 ： 形成环，头节点偏移 size - ( k % size) 位，
     *        最后斩断环
     * 时间复杂度 ： O(n)
     */
    public static ListNode circleSoluting(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        //游标节点 , 链表大小, 当前的前驱
        ListNode cursorNode = head, preNode = head;
        int size = 1;
        //总数
        while (cursorNode.next != null) {
            cursorNode = cursorNode.next;
            size++;
        }
        //形成环
        cursorNode.next = preNode;
        //计算偏移量
        int offset = size - (k % size);
        //游标节点和前驱默认初始化
        cursorNode = preNode = head;
        //定位新的头节点
        for (int i = 0; i < offset; i++) {
            preNode = cursorNode;
            cursorNode = cursorNode.next;
        }
        //斩断环
        preNode.next = null;
        return cursorNode;
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
