package com.zhouhc.speedpointer;


import java.util.HashSet;
import java.util.Set;

/**
 *q141_环形链表
 *   核心思想 : 有两种方式进行求解，一种是
 *      移动判断时，一种是使用不同速率的指针
 */
public class CircleLink {

    public static void main(String[] args) {
        ListNode head = getListNode(new int[]{3, 2, 0, -4}, 1);
        System.out.println("哈希表方式 : " + findCircleByHash(head));
        System.out.println("哈希表方式 : " + findCircleBySpeed(head));
    }

    /**
     * 自己想的
     *  哈希表方式 : 需要维护一个字典表，用于判断
     *      该元素是否已经出现过了，每移动一个元素都需要判断
     *  时间复杂度 : O(n) , 空间复杂度 : O(n)
     */
    private static boolean findCircleByHash(ListNode head) {
        //声明哈希表,也就是字典
        Set<ListNode> elementSet = new HashSet<ListNode>();
        //指针判断
        while (head != null) {
            //判断是否包含
            if (!elementSet.add(head))
                return true;
            head = head.next;
        }
        return false;
    }

    /**
     *  快慢指针解法 : 需要每次移动速率不同的指针(一般快的是慢的速率的两倍),
     *   如果链表存在环，那么一定会存在 快指针 比 慢指针 更先进入环，并且因为环
     *   的存在，快指针会在某个时间点 和 慢指针相遇，改时间点下，快指针比慢指针
     *   领先若干圈，必须注意的是快慢指针任一一个改变，都得比较。
     *   时间复杂度 : O(n) 空间复杂度 : O(1)
     *   ps : 顺便说一下求入口环的索引 , 这里假设 从链表头 到 环入口的 距离 为 x,
     *    从环口到相遇点的 距离 为 y ,  从 相遇点 到环入口的另一个半距离为 z 。
     *    因为 fast 移动速率是 low 的两倍 , fast 和 low 相遇时 , fast 比 low
     *    多走了 n 个环的倍数 ，可以得到下面的结论 ：
     *      (x+y)* 2 = x + y + n ( y + z )
     *    等式左边是low走的距离， 右边为fast走的距离。 n表示未知 , 不能确定出 fast
     *    比 low 多走了 几圈 环的路径。
     *    等式 两边各去掉一个 x+y 可以得到
     *     x + y = n ( y + z )
     *     变形一下
     *     x = n ( y + z ) - y 。现在只关注右边，只要右边 在减去 一个 z ，就等于
     *     环的n -1 倍，也就是到环入口的距离 :  (n-1)(y+z) = n (y +z) - y - z。
     *     所以 x 又是 头节点 到 环口的距离。 也就是从 在另一个指针 low' 以 low的速率
     *     从 头节点 到 环口出， low从与 fast相遇点 继续以 相同的速率走下去 ，low' 和
     *     low 会在环口相遇。
     *     通过上面就可以求出 环入口 的位置了。
     */
    private static boolean findCircleBySpeed(ListNode head) {
        //声明两个不同的指针
        ListNode fastSpeedPointer = head;
        ListNode lowSpeedPoniter = head;
        //标识慢指针是否需要移动
        boolean isLowMove = true;
        //开始循环
        while (fastSpeedPointer != null) {
            //快指针移动
            fastSpeedPointer = fastSpeedPointer.next;
            //任一指针的移动都需要判断
            if (lowSpeedPoniter == fastSpeedPointer)
                return true;
            //修改慢指针移动的标志
            if (isLowMove) {
                isLowMove = false;
            } else {
                //证明快指针已经移动了，所以慢 指针就可以移动了
                isLowMove = true;
                //慢指针移动
                lowSpeedPoniter = lowSpeedPoniter.next;
                //任一指针的移动都需要判断
                if (lowSpeedPoniter == fastSpeedPointer)
                    return true;
            }
        }
        return false;
    }


    //获取链表
    private static ListNode getListNode(int[] nums, int pos) {
        ListNode headPointer = new ListNode(2222);
        ListNode tailPointer = headPointer;
        //开始创建节点
        for (int i = 0; i < nums.length; i++) {
            //节点赋值
            tailPointer.next = new ListNode(nums[i]);
            //后移尾结点
            tailPointer = tailPointer.next;
        }
        //构成环
        ListNode insertNode = headPointer.next;
        for (int i = 0; i < pos; i++)
            insertNode = insertNode.next;
        tailPointer.next = insertNode;
        //构建环
        return headPointer.next;
    }

    //内部节点表 ， 外部类不可见
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
