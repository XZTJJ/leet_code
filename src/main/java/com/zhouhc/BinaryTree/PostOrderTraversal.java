package com.zhouhc.BinaryTree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * q145_后序遍历
 * 递归简单，非递归才是最麻烦的
 */
public class PostOrderTraversal {

    //全局变量存储数据
    private static final List<Integer> result = new ArrayList<Integer>();

    /**
     * 后序遍历,递归版
     */
    public static List<Integer> postorderTraversalByRec(TreeNode root) {
        if (root == null)
            return result;
        //左右两边分别迭代
        postorderTraversalByRec(root.left);
        postorderTraversalByRec(root.right);
        //保存根
        result.add(root.val);
        return result;
    }


    /**
     * 后序遍历,迭代版
     * 核心思想 :
     *   1.第一步，从左下找起，如果该节点不为空的话，入栈。
     *   2.找到 左孩子为空的节点A，A出栈，获取A 的右孩子B
     *   3.如果B为空或者已经记录过了，那么将A的放入结果集，并将A置空；如果A的右孩子不为空，重复 1,2两部
     *   4.依次循环，知道栈中的元素为空
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
            //处理右孩子已经遍历的情况或者右孩子为空的情况
            if(tempNode.right == null || tempNode.right == preNode){
                //放入结果集中
                result.add(tempNode.val);
                //记录已经遍历过的右节点，防止重复遍历
                preNode = tempNode;
                //同时要将节点置空，防止，出现左遍历
                tempNode = null;
            }else{
                //不为空的情况，那么就需要将父节点加入
                stack.push(tempNode);
                tempNode = tempNode.right;
            }
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
