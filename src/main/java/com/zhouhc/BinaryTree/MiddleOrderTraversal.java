package com.zhouhc.BinaryTree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * q99_前序遍历
 * 递归简单，非递归才是最麻烦的
 */
public class MiddleOrderTraversal {

    //全局变量存储数据
    private static final List<Integer> result = new ArrayList<Integer>();

    /**
     * 前序遍历,递归版
     */
    public static List<Integer> postorderTraversalByRec(TreeNode root) {
        if (root == null)
            return result;
        //左右两边分别迭代
        postorderTraversalByRec(root.left);
        //保存根
        result.add(root.val);
        postorderTraversalByRec(root.right);

        return result;
    }


    /**
     * 前序遍历,迭代版
     * 核心思想 :
     * 1.第一步，从左下找起，如果该节点不为空的话，入栈。
     * 2.找到 左孩子为空的节点A，A出栈，记录A 的值，获取A 的右孩子B
     * 3.如果B不为空的话，重复执行1,2步骤
     * 4.依次循环，知道栈中的元素为空
     */
    public static List<Integer> preorderTraversalByIte(TreeNode root) {
        //使用栈的特性，就像递归版本的一样，添加的子节点，注意是子节点不为空才添加
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode tempNode = root;
        //保留最近遍历的右孩子节点
        TreeNode preNode = null;
        //循环遍历 ,一直递归知道左右孩子为null,为空就证明左右孩子，其中有一个为空了
        while (stack.size() > 0 || tempNode != null) {
            //左孩子一直递归处理,一直到左孩子为空
            while (tempNode != null) {
                stack.add(tempNode);
                tempNode = tempNode.left;
            }
            //定义要弹出元素
            tempNode = stack.pop();
            //记录结果
            result.add(tempNode.val);
            //处理右孩子的情况
            tempNode = tempNode.right;

        }
        return result;
    }


    /**
     * 树节点
     */
    private static class TreeNode {
        //数据域
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
