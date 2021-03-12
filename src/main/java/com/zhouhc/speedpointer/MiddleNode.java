package com.zhouhc.speedpointer;

import sun.text.normalizer.CharacterIteratorWrapper;

/**
 * q876_链表的中间结点
 * 核心思想 : 快慢指针，这样快指针走完了，慢指针刚好走一半
 *         别只记得快慢指针只能处理环的问题
 * 时间复杂度 : O(n)，空间复杂度 : O(1)
 */
public class MiddleNode {


    public static void main(String[] args) {
        ListNode listNode = getListNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(findMiddleNodeBySpeed(listNode).val);
    }

    //通过快慢指针的方式 找出中间结点
    private static ListNode findMiddleNodeBySpeed(ListNode head) {
        //声明快慢指针
        ListNode fastSpeed = head, lowSpeed = head;
        //慢指针是否移动标识
        boolean isLowSpeed = true;
        //循环遍历
        while (fastSpeed != null) {
            //快指针移动
            fastSpeed = fastSpeed.next;
            //快指针是慢指针移动速率的两倍
            if (isLowSpeed) {
                isLowSpeed = false;
            } else {
                isLowSpeed = true;
                lowSpeed = lowSpeed.next;
            }
        }
        //指针返回
        return lowSpeed;
    }

    //构建链表
    private static ListNode getListNode(int[] nums) {
        ListNode headPointer = new ListNode(2222);
        ListNode tailPointer = headPointer;
        //开始创建节点
        for (int i = 0; i < nums.length; i++) {
            //节点赋值
            tailPointer.next = new ListNode(nums[i]);
            //后移尾结点
            tailPointer = tailPointer.next;
        }
        //构建环
        return headPointer.next;
    }

    //内部结点
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
