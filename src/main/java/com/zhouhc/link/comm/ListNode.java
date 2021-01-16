package com.zhouhc.link.comm;

/**
 * 链表节点类，基础类
 */
public class ListNode {
    //指针域
    public ListNode next;
    //数据域
    public int val;

    public ListNode() {
    }

    public ListNode(int val, ListNode next) {
        this.next = next;
        this.val = val;
    }
}
