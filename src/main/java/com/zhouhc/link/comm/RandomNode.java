package com.zhouhc.link.comm;

//双指针的链表节点
public class RandomNode {
    //数据域
    public int val;
    //下一个指针域
    public RandomNode next;
    //随机指针域 或者 其他指针域
    public RandomNode random;

    public RandomNode(int val) {
        this.val = val;
    }

    public RandomNode(int val, RandomNode next, RandomNode random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}
