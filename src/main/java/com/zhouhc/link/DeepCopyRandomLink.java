package com.zhouhc.link;

import com.zhouhc.link.comm.RandomNode;

import java.util.HashMap;
import java.util.Map;

/**
 * q138_复制带有随机指针的链表
 * 棘手 : 随机指针的处理
 */
public class DeepCopyRandomLink {

    //全局保存对象用于 回溯算法 用到的 映射或者访问的记录
    private static final Map<RandomNode, RandomNode> backtrackMap = new HashMap<RandomNode, RandomNode>();

    public static void main(String[] args) {
        RandomNode head = getListData(new int[][]{{7, Integer.MIN_VALUE}, {13, 0},
                {11, 4}, {10, 2}, {1, 0}});
        //推荐,回溯思想
        //RandomNode headNext = backtrackingRandomList(head);
        // 利用栈特性
        //RandomNode headNext = recursionRandomList(head);
        //注重空间算法
        RandomNode headNext = iterationRandomList(head);
        System.out.println("新的节点:" + headNext);
    }

    /**
     * 回溯思想
     * 核心思想 : 非常类似图的 深度遍历 算法，从某个节点开始，一致递归处理
     *           直到处理完成，然后在回溯处理递归中遇到的节点(栈思想，递归先进后出)，
     *           rand 和 next 处理的顺序是 随意的，不需要指定。
     *           需要维护一个字典表
     * 时间复杂度 : O(n) , 空间复杂度 : O(n)
     * ps : 其实下面空间遍历算法(recursionRandomList) 空间复杂度差不多，
     *      但是两个思想不一样, recursionRandomList只是利用了栈的性质，并没有
     *      实际回溯的特性
     */
    private static RandomNode backtrackingRandomList(RandomNode head) {
        //为空，证明没有, 为了避免栈溢出，应该尽量少生命变量
        if (head == null)
            return head;
        //查看是否为空，不为空的话，证明已经创建了并且处理了，直接返回
        if (backtrackMap.containsKey(head))
            return backtrackMap.get(head);
        //如果没有创建，需要先创建并且处理整个节点， 包括 next 和 rand 节点
        RandomNode currentNode = new RandomNode(head.val);
        backtrackMap.put(head, currentNode);
        // 递归 和 回溯random 和 next。 他们顺序节点是任意的 ，因为 next 存在会遍历整个链表，
        currentNode.random = backtrackingRandomList(head.random);
        currentNode.next = backtrackingRandomList(head.next);
        //始终返回当前节点
        return backtrackMap.get(head);
    }


    /**
     * 空间遍历算法
     * 核心思想 : 利用递归的特性，先遍历创建所有的节点，
     *           然后从尾结点逆序逐一赋值， 需要维护一个字典表
     * 时间复杂度 : O(n) , 空间复杂度 : O(n)
     */
    private static RandomNode recursionRandomList(RandomNode head) {
        //为空，证明没有
        if (head == null)
            return head;
        //创建对应的节点
        backtrackMap.putIfAbsent(head, new RandomNode(head.val));
        //为了避免尾递归，先提前递归
        recursionRandomList(head.next);
        //获取对应的节点
        RandomNode currentNode = backtrackMap.get(head);
        //设置next 和 rand 值
        currentNode.next = backtrackMap.get(head.next);
        currentNode.random = backtrackMap.get(head.random);
        //始终返回当前节点
        return backtrackMap.get(head);
    }


    /**
     * 空间遍历算法,注重空间
     * 核心思想 :  先考虑 正确初始化 rand 节点，在考虑 next 节点，
     *            充分利用源节点的,  首先  copy 一份节点，这里的 copy
     *            是将新 copy 出的节点，指向源节点的next,然后源节点的next
     *            指向 copy的新节点。整个链表复制完成以后(A -> A' -> B -> B')， copy的random
     *            就是 源节点 random 的next ，处理好 random 后，在正确处理
     *            next节点，next需要交叉处理,最后一个节点需要单独处理
     * 时间复杂度 : O(n) , 空间复杂度 : O(1)，
     * ps : 时间上要比上面两种算法来说要慢一下，因为要读遍历一次链表
     */
    private static RandomNode iterationRandomList(RandomNode head) {
        //首先copy一份链表，链表形式为 A -> A' -> B -> B'
        if (head == null)
            return head;
        //游标节点，正式开始复制
        RandomNode cursorNode = head;
        while (cursorNode != null) {
            //形成 A -> A' -> B  形式
            RandomNode _cursorNode = new RandomNode(cursorNode.val);
            _cursorNode.next = cursorNode.next;
            cursorNode.next = _cursorNode;
            //处理 cursorNode 的赋值，需要等于 _cursorNode.next才行
            cursorNode = _cursorNode.next;
        }

        //链表复制完成，正确初始化 rand 节点
        cursorNode = head;
        while (cursorNode != null) {
            // copy 节点的 randon 为源节点random 的next
            RandomNode _cursorNode = cursorNode.next;
            if (cursorNode.random != null) {
                _cursorNode.random = cursorNode.random.next;
            }
            cursorNode = _cursorNode.next;
        }

        //处理正确的next节点, cursorNode用于遍历源链表节点， _cursorNode用于遍历新链表节点，
        cursorNode = head;
        RandomNode newHead = cursorNode.next;
        RandomNode _cursorNode = newHead;
        //需要用新的链表来遍历，为了处理空指针问题
        while (_cursorNode.next != null) {
            //链表形式为  A -> A' -> B -> B'
            //处理旧的节点
            cursorNode.next = _cursorNode.next;
            cursorNode = cursorNode.next;
            //处理新的节点
            _cursorNode.next = _cursorNode.next.next;
            _cursorNode = _cursorNode.next;
        }
        cursorNode.next = _cursorNode.next;

        //始终返回当前节点
        return newHead;
    }

    /**
     * 数据拼接
     */
    private static RandomNode getListData(int[][] nums) {
        //存放所有节点,随机节点不按照顺序存放，所以需要先提前申请空间
        RandomNode[] allNode = new RandomNode[nums.length];
        //执行后一个节点
        RandomNode tailPoint = new RandomNode(Integer.MIN_VALUE);
        //遍历创建，同时创建当前节点和随机节点
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i][0];
            int randIndex = nums[i][1];
            //创建当前节点
            RandomNode currenNode = allNode[i];
            //不存在就创建，如果存在证明是前面随机节点创建的
            if (currenNode == null) {
                currenNode = new RandomNode(Integer.MIN_VALUE);
                allNode[i] = currenNode;
            }
            //修改数据域的值,每个都要修改，不会修改错的，只会在这修改值且只修改一遍
            currenNode.val = value;

            //随机节点
            RandomNode randomNode = null;
            //指向有效的索引，才触发创建或者修改的操作
            if (randIndex >= 0) {
                //节点不存在则创建
                if (allNode[randIndex] == null) {
                    //数据域的值延迟到顺序创建的时候才修改,其他地方不做修改
                    randomNode = new RandomNode(Integer.MIN_VALUE);
                    allNode[randIndex] = randomNode;
                } else {
                    //存在直接使用
                    randomNode = allNode[randIndex];
                }
            }
            //随机指针的赋值
            currenNode.random = randomNode;
            //确保next指针的正常，工作
            tailPoint.next = currenNode;
            tailPoint = currenNode;
        }
        //首节点
        return allNode[0];
    }
}
