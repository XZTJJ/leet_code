package com.zhouhc.speedpointer;

/**
 * q142_环形链表II
 * <p>
 * 核心思想 : 快慢指针(2倍) ,可以发现，当快慢指针在环中相交的时候
 * ,让慢指针继续进行 , 同时让一个新的指针从开始节点以慢指针同样的速度
 * 出发，他们必然相交于环入口
 * <p>
 * 时间复杂度 : O( n ) , 空间复杂度 :O( 1 )
 */
public class CircleLinkII {


    //快慢指针的思想
    public static ListNode detectCycle(ListNode head) {
        //开始使用
        ListNode fastPointer = head, slowPointer = head;
        //循环，找到相交的地方
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
            //证明他们在环内某个节点相交了
            if (fastPointer == slowPointer)
                break;
        }
        //判断是否相交
        if (fastPointer == null || fastPointer.next == null)
            return null;
        ListNode tempPointer = head;
        while (tempPointer != slowPointer) {
            tempPointer = tempPointer.next;
            slowPointer = slowPointer.next;
        }
        return tempPointer;
    }

    //链表节点
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
