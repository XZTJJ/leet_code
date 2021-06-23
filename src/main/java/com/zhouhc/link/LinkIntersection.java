package com.zhouhc.link;


/**
 * q02.07_链表相交
 */
public class LinkIntersection {


    /**
     * 自己的想法 :
     * 题目中的意思是 : 如果相交的话，链表尾部是一定有一部分重合的
     * 因此可以利用这个特征，进行判断。
     * 时间复杂度 : O(n) , 空间复杂度 : O(n)
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //空的话不需要判断
        if (headA == null || headB == null)
            return null;
        //记录长度
        int sizeA = 0, sizeB = 0;
        ListNode currentA = headA, preA = null;
        ListNode currentB = headB, preB = null;
        //记录A链表长度
        while (currentA != null) {
            sizeA++;
            preA = currentA;
            currentA = currentA.next;
        }
        //记录B的长度
        while (currentB != null) {
            sizeB++;
            preB = currentB;
            currentB = currentB.next;
        }
        //如果结尾相等，那么一定相交
        if (preA != preB)
            return null;
        //开始找交点
        currentA = preA = headA;
        currentB = preB = headB;
        //谁长谁移动,找到长度一样的节点
        int tempValue = sizeA - sizeB;
        if (tempValue <= 0) {
            for (int i = tempValue; i < 0; i++)
                currentB = currentB.next;
        } else {
            for (int i = 0; i < tempValue; i++)
                currentA = currentA.next;
        }
        //开始比较
        while (currentA != null) {
            if (currentA == currentB)
                break;
            currentA = currentA.next;
            currentB = currentB.next;
        }
        return currentA;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
