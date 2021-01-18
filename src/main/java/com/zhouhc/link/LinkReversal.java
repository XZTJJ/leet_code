package com.zhouhc.link;

import com.zhouhc.link.comm.ListNode;


/**
 * q206_反转链表
 *
 * ps : 对于链表而言，不一定需要头指针,需要操作头结点的前一个指针时，
 *      为了不特殊判断就可以使用头指针了。
 *      只要不是使用new,克隆,序列化不算使用新的空间, 因为都是在
 *      原来的操作上进行的, 所以不算是使用新的空间,引用不占用空间。
 *
 *
 */
public class LinkReversal {

    public static void main(String[] args) {
        ListNode listData = getListData(new int[]{1, 2, 3, 4});
        //迭代
        //iterationSolution(listData);
        //递归
        System.out.println(listData);
    }


    /**
     * 迭代
     * 思路 : 当前节点后继(.next)指向 前驱, 然后当前节点变成了前驱
     *       当前节点的后继变成当前节点,这样依次循环
     * 时间复杂度 : O(n)
     */
    public static ListNode iterationSolution(ListNode head) {
        //声明前驱(默认值为空) , 当前节点(默认为原节点)
        ListNode preNode = null, currNode = head;
        //只要当前节点不为空，就证明链表还没有到尾部
        while (currNode != null) {
            //获取后驱
            ListNode nextNode = currNode.next;
            //修改当前的后继 为 源当前节点的前驱
            currNode.next = preNode;
            /**
             * 当前节点已经处理完了，所以当前节点要变成前驱，
             * 当前节点的后继变成当前节点， 这样依次循环
             */
            preNode = currNode;
            currNode = nextNode;
        }
        return preNode;
    }

    /**
     * 递归
     * 思路 : 其实和递归一样，不过需要从后往前进行处理才行，
     *       核心就是 nk.next.next = nk , nk.next = null(防止出现环,源头结点那里)
     * 时间复杂度 : O(n) , 空间复杂度 :O(1)
     *
     */
    public static ListNode recursionSolution(ListNode head) {
        //返回头结点
        if (head == null || head.next == null)
            return head;
        //始终返回头结点，不去修改这个值
        ListNode headPoint = recursionSolution(head.next);
        //设置倒转指向  nk.next.next = nk
        head.next.next = head;
        //防止产生环，主要是源头结点那里
        head.next = null;
        //始终为头结点
        return headPoint;
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
