package com.zhouhc.link;


/**
 * q707_设计链表
 * <p>
 * 核心思想 : 使用 头尾指针 和 size
 * 方式进行操作。
 */
public class MyLinkedList {
    //大小
    private int size = 0;
    //头节点
    private Node head = new Node(Integer.MIN_VALUE, null, null);
    //尾结点
    private Node tail = new Node(Integer.MAX_VALUE, null, null);

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3, 0);
        myLinkedList.deleteAtIndex(2);
        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        myLinkedList.get(4);
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5, 0);
        myLinkedList.addAtHead(6);
        System.out.println("");
    }

    public MyLinkedList() {
        //头尾节点关联
        head.next = tail;
        tail.prev = head;
    }

    //获取元素
    public int get(int index) {
        Node node = getNode(index);
        return node == null ? -1 : node.data;
    }


    //查找某个元素
    private Node getNode(int index) {
        if (index < 0 || index >= size)
            return null;
        Node tempNode;
        //从头定位元素
        if (index <= size / 2) {
            tempNode = head;
            for (int i = 0; i <= index; i++)
                tempNode = tempNode.next;
        } else {
            //从尾部定位元素,注意因为 size 其实是比index 大 1 的
            tempNode = tail;
            for (int i = size; i > index; i--)
                tempNode = tempNode.prev;
        }
        return tempNode;
    }

    //往头部添加
    public void addAtHead(int val) {
        Node insertNode = new Node(val, head.next, head);
        head.next.prev = insertNode;
        head.next = insertNode;
        size++;
    }

    //往尾部添加
    public void addAtTail(int val) {
        Node insertNode = new Node(val, tail, tail.prev);
        tail.prev.next = insertNode;
        tail.prev = insertNode;
        size++;
    }

    //随机添加
    public void addAtIndex(int index, int val) {

        if (index <= 0) {
            //添加头部
            addAtHead(val);
        } else if (index == size) {
            //添加尾部
            addAtTail(val);
        } else {
            Node tempNode = getNode(index);
            if (tempNode == null)
                return;
            //开始插入元素
            Node inserNode = new Node(val, tempNode, tempNode.prev);
            tempNode.prev.next = inserNode;
            tempNode.prev = inserNode;
            //数量+1
            size++;
        }
    }

    //删除元素
    public void deleteAtIndex(int index) {
        Node tempNode = getNode(index);
        if (tempNode == null)
            return;
        tempNode.prev.next = tempNode.next;
        tempNode.next.prev = tempNode.prev;
        //删除引用
        tempNode.next = tempNode.prev = null;
        tempNode = null;
        //数量+1
        size--;
    }


    //节点类
    private static class Node {
        int data;
        Node next;
        Node prev;

        public Node() {
        }

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
