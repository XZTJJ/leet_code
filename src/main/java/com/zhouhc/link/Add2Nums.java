package com.zhouhc.link;


import com.zhouhc.link.comm.ListNode;

/**
 * q2_两数相加
 */
public class Add2Nums {

    public static void main(String[] args) {
        //制造数据
        //测试一
        //ListNode l1 = getListData(new int[]{2, 4, 3});
        //ListNode l2 = getListData(new int[]{5,6,4});
        //测试二
        //ListNode l1 = getListData(new int[]{0});
        //ListNode l2 = getListData(new int[]{0});
        //测试三
        ListNode l1 = getListData(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode l2 = getListData(new int[]{9, 9, 9, 9});
        //真实的求解过程,推荐方式
        //solution(l1, l2);
        //不创建新节点的求解方式,推荐方式
        solutionWhitNoCreate(l1,l2);
    }

    /**
     * 遍历求解
     * 思路 : 遍历两个链表，为了保持有序，使用尾插法
     *      10进制存在进一位的思想，额外需要一个int变量
     *      保存是否需要进位，默认为0。
     * 时间复杂度 : O(max(n,m)) ,ps : n,m为两个链表的长度
     */

    private static ListNode solution(ListNode l1, ListNode l2) {
        //设置头指针,尾指针，头指针的作用为左右节点操作一直，尾指针是为了保持有序
        ListNode headPoint, tailPoint;
        //初始化头尾节点
        headPoint = tailPoint = new ListNode(0, null);
        //是否需要进位
        int carry = 0;
        //确保遍历完所有的数组
        while (l1 != null || l2 != null) {
            //获取末尾数之和
            int data = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            //创建节点，修改尾指针域，保持尾指针始终指向最后一个元素
            tailPoint.next = new ListNode(data % 10, null);
            tailPoint = tailPoint.next;
            //每次都要修改carry值
            carry = data / 10;
            //让l1 和 l2 指向下一个元素
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        //处理进位的操作
        if (carry != 0) {
            tailPoint.next = new ListNode(carry, null);
            tailPoint = tailPoint.next;
            carry = 0;
        }
        //headPoint是头指针
        return headPoint.next;
    }


    /**
     * 在原有的链表上操作,不创建新的链表
     */
    private static ListNode solutionWhitNoCreate(ListNode l1, ListNode l2) {
        //l1 和 l2的备份
        ListNode l1Head = l1;
        ListNode l2Head = l2;
        //最终返回的表头
        ListNode reulstNode = null;
        //最后一个元素的上一个节点
        ListNode preNode = null;
        //是否需要进位
        int carry = 0;
        //确保遍历完所有的数组
        while (l1 != null || l2 != null) {
            //获取末尾数之和
            int data = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            //每次都要修改carry值
            carry = data / 10;
            //让l1 和 l2 指向下一个元素
            if (l1 != null) {
                //为l1当前节点赋值,设置头节点，保留前一个节点,并且指针偏移
                l1.val = data % 10;
                reulstNode = l1Head;
                preNode = l1;
                l1 = l1.next;
            }
            if (l2 != null) {
                //为l2当前节点赋值,设置头节点，保留前一个节点,并且指针偏移
                l2.val = data % 10;
                reulstNode = l2Head;
                preNode = l2;
                l2 = l2.next;
            }
        }


        //处理进位的操作
        if (carry != 0) {
            preNode.next = new ListNode(carry,null);
            carry = 0;
        }
        //reulstNode是头指针
        return reulstNode;
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
