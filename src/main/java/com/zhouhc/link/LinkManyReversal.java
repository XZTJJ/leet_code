package com.zhouhc.link;

import com.zhouhc.link.comm.ListNode;

/**
 * q25_K个一组翻转链表
 * ps : 对于链表而言，不一定需要头指针,需要操作头结点的前一个指针时，
 *      为了不特殊判断就可以使用头指针了。
 */
public class LinkManyReversal {

    public static void main(String[] args) {
        ListNode l1 = getListData(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        //写这么多主要是为了体现头指针的好处
        //solution(l1, 3);
        //不算非常完美的写法，
        //solutionProWithSize(l1, 3);
        solutionProWithNoSize(l1, 3);
    }


    /**
     * 思路非常复杂
     *  1. range 元素组成一个链表 A ，需要两个临时变量nextNode,currentGroupHead ，
     *  2. headNode 为源链表的头节点 , 需要注意只要在处理 小组 后才向后偏移，
     *      在小组内其实headNode已经被修改了next的指针域了
     *  3. nextNode的next是保证始终指向 A 的 头结点， currentGroupHead始终为 A 的头结点
     *     需要注意的是 currentGroupHead 为空是， nextNode的next 指向 。
     *  4. 需要一个额外的节点来保存新整个链表的头节点，也就是rootNode, 还需要一个处理
     *     衔接 小组间的临时节点 lastGroupHead ，lastGroupHead为空 表示正在处理源整个链表的头节点，
     *     currentGroupHead 为新链表的头节点， rootNode 赋值为  currentGroupHead 。否则 上一个小组
     *     的头结点 lastGroupHead.next 应该指向 当前小组的头结点 currentGroupHead。 处理完成以后记得修改
     *     lastGroupHead 为下一个小组的源头节点。
     *  思路总结 :  以range为小组翻转，处理上一个小组源头节点和下一个小组的新头节点的指向关系。
     *  总结 : 写这么多是为了体现头指针的好处。感觉代码设计的十分的非常，基本上也不会去看了
     *
     */
    private static ListNode solution(ListNode l1, int range) {
        //headNode遍历的作用
        ListNode headNode = l1;
        //获取总数
        int size = 0;
        while (headNode != null) {
            size++;
            headNode = headNode.next;
        }
        //指向初始的头节点
        headNode = l1;
        //保存新链接的头节点 rootNode , lastGroupHead 上一个小组的源头节点
        ListNode rootNode = null, lastGroupHead = null;
        while (headNode != null) {
            //当前小组的调整后的头节点
            ListNode currentGroupHead = null;
            //range为单位，每个单位为一组，对组内元素进行调整
            for (int i = 1; i < range && size >= range; i++) {
                //转存中间数据
                ListNode nextNode = headNode.next;
                headNode.next = nextNode.next;
                //处理头节点 和 普通节点 ，始终保持 nextNode.next 为新头节点
                if (currentGroupHead == null) {
                    nextNode.next = headNode;
                } else {
                    nextNode.next = currentGroupHead;
                }
                //currentGroupHead始终为头结点
                currentGroupHead = nextNode;
                //下一个节点继续,其实headNode的next被修改了，所以不要后移
            }
            //处理源链接的头节点的情况
            if (lastGroupHead == null) {
                //rootNode 指向新头,保证能获取获取到链
                rootNode = currentGroupHead;
            } else {
                //上一个小组的最后一个元素(也就是小组没有调整前的头节点),指向新的头结点
                lastGroupHead.next = currentGroupHead;
            }
            //保存一下组的没有调整前的头节点
            lastGroupHead = headNode;
            //处理长度,并且后移
            size -= range;
            headNode = headNode.next;
            //如果数量不足,直接拼接
            if (size < range) {
                while (headNode != null)
                    headNode = headNode.next;
            }
        }

        return rootNode;
    }


    /**
     *  思路 : 和上面的一样，同样以range为小组，翻转小组内的元素，
     *        这次带有头指针，记得所有的链表操作都要带有头指针
     *  思路总结 :  以range为小组翻转，处理上一个小组源头节点(也就是上个小组翻转后的最后一个元素)
     *             和下一个小组的新头节点的指向关系。
     *  时间复杂度 : O(n)，空间复杂度 : O(1)
     */
    private static ListNode solutionProWithSize(ListNode l1, int range) {
        //生命头指针
        ListNode headPoint = new ListNode(-10000, l1);
        int size = 0;
        //统计总数
        while (headPoint.next != null) {
            size++;
            headPoint = headPoint.next;
        }
        //头指针重新赋值
        headPoint = new ListNode(-10000, l1);
        //小组的头节点，初始化为整体的头节点
        ListNode groupHeadPoint = headPoint;
        //上个小组的源节点，初始化为头指针, 处理小组间衔接为，保证上个小组的源头节点(其实也就是小上个组翻转后的最后一个元素)指向当前小组的翻转后的头节点
        ListNode oriPreHeadNode = headPoint;
        //开始计算,以range个元素为小组
        while (size >= range) {
            //小组内翻转,游标cursorNode, cursorNode.next始终翻转节点的后继
            ListNode cursorNode = groupHeadPoint.next;
            //小组间的翻转
            for (int i = 1; i < range; i++) {
                //获取调整节点,修改 cursorNode.next 指向 调整节点的下一个元素
                ListNode nextNode = cursorNode.next;
                cursorNode.next = nextNode.next;
                //调整节点的 next 始终指向头部，并且自身也要升级成头部
                nextNode.next = groupHeadPoint.next;
                groupHeadPoint.next = nextNode;
            }
            //小组翻转完成，保证上个小组的源头节点(其实也就是小上个组翻转后的最后一个元素)指向当前小组的翻转后的头节点
            oriPreHeadNode.next = groupHeadPoint.next;
            //处理下一个小组，也就是设置下一个小组的头指针，使用new一个对象是为了保持 headPoint 不乱
            groupHeadPoint = new ListNode(-10000, cursorNode.next);
            //记录上个小组的源头节点，方便指向下一个小组翻转后的节点
            oriPreHeadNode = cursorNode;
            //缩小范围
            size -= range;
        }

        return headPoint;
    }


    /**
     *  思路 : 和上面的一样，同样以range为小组，翻转小组内的元素，
     *        这次带有头指针，记得所有的链表操作都要带有头指针
     *  思路总结 :  以range为小组翻转，处理上一个小组源头节点(也就是上个小组翻转后的最后一个元素)
     *             和下一个小组的新头节点的指向关系。
     *  时间复杂度 : O(n)，空间复杂度 : O(1)
     */
    private static ListNode solutionProWithNoSize(ListNode head, int range) {
        //声明头指针
        ListNode headPoint = new ListNode(0, head);
        /**
         * 上个小组的源节点，初始化为头指针, 处理小组间衔接为，
         * 保证上个小组的源头节点(其实也就是小上个组翻转后的最后一个元素)指向当前小组的翻转后的头节点
         */
        ListNode oriPreHeadNode = headPoint;

        //遍历用
        ListNode iterationNode = oriPreHeadNode.next;

        while (iterationNode != null) {
            //游标的头节点
            ListNode cursorHeadNode = iterationNode;
            //判断是否需要小组翻转
            for (int i = 0; i < range - 1 && iterationNode != null; i++)
                iterationNode = iterationNode.next;
            //为空证明，小组的数量不到翻转的数量，直接跳过
            if (iterationNode == null)
                continue;
            //游标的尾节点
            ListNode cursorTailNode = iterationNode;

            //开始翻转
            reversalGrouop(cursorHeadNode, cursorTailNode);
            /**
             * 小组翻转完成，保证上个小组的源头节点(其实也就是小上个组翻转后的最后一个节点)
             *  指向当前小组的翻转后的头节点.
             *  ps : 其实也就是 翻转后的小组链表的头节点(其实也是源小组的最后一个节点,
             *        Java的引用传递保证了next的正确性)
             */
            oriPreHeadNode.next = cursorTailNode;
            /**
             * 记录上个小组的源头节点，方便指向下一个小组翻转后的节点
             * ps ： 其实也就是 翻转后的小组链表的最后一个节点(其实也是源小组的头节点,
             *       Java的引用传递保证了next的正确性)
             */
            oriPreHeadNode = cursorHeadNode;
            //给iterationNode恢复到原始链表的下一个节点，进行下一组的计算
            iterationNode = oriPreHeadNode.next;
        }
        return headPoint.next;
    }

    /**
     * 翻转处理逻辑 , 当前节点后继(.next)指向 前驱, 然后当前节点变成了前驱
     *              当前节点的后继变成当前节点,这样依次循环
     */
    private static void reversalGrouop(ListNode cursorHeadNode, ListNode cursorTailNode) {
        //记录前驱,默认值为最后一个
        ListNode preNode = cursorTailNode.next;
        //记录当前节点
        ListNode currNode = cursorHeadNode;
        //翻转, 核心是 前驱变成后继，后继变成当前节点
        while (currNode != cursorTailNode) {
            //当前节点指向前驱
            ListNode nextNode = currNode.next;
            currNode.next = preNode;
            //当前节点变成前驱， 后继变成当前节点
            preNode = currNode;
            currNode = nextNode;
        }
        //处理最后一个节点的前驱问题
        currNode.next = preNode;
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
