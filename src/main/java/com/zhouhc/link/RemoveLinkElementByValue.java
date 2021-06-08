package com.zhouhc.link;

import java.util.stream.Stream;

/**
 * q203_移除链表元素
 */
public class RemoveLinkElementByValue {

    public static void main(String[] args) {
//        ListNode listData = getListData(new int[]{1, 2, 6, 3, 4, 5, 6});
//        listData = removeElements(listData,6);

        ListNode listData = getListData(new int[]{7,7,7,7});
        listData = removeElements(listData,7);
        System.out.println("");
    }

    /**
     * 核心思想 : 为了方便操作第一个元素，需要头指针
     * 然后就是逐一删除,需要记录当前元素 和 上一个元素
     * 方便删除
     * <p>
     * 时间复杂度 : O(n)  空间复杂度 : O(1)
     */
    public static ListNode removeElements(ListNode head, int val) {
        //申明头节点
        ListNode headPinoter = new ListNode(0, head);
        //当前元素 ，前一个元素
        ListNode preNode = headPinoter, currentNode = headPinoter.next;
        //循环
        while (currentNode != null) {
            //不相等，证明符合要求，currentNode升级成preNode
            if (currentNode.val != val)
                preNode = currentNode;
            else
                preNode.next = currentNode.next;
            //下一个元素变成当前元素
            currentNode = currentNode.next;
        }
        return headPinoter.next;
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

    //元素节点
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
