package com.zhouhc.BinaryTree;


import java.util.*;

/**
 * q144_前序遍历
 * 递归简单，非递归才是最麻烦的
 */
public class PreOrderTraversal {

    //全局变量存储数据
    private static final List<Integer> result = new ArrayList<Integer>();

    /**
     * 前序遍历,递归版
     */
    public static List<Integer> preorderTraversalByRec(TreeNode root) {
        if (root == null)
            return result;
        //保存根
        result.add(root.val);
        //左右两边分别迭代
        preorderTraversalByRec(root.left);
        preorderTraversalByRec(root.right);
        return result;
    }


    /**
     * 前序遍历,迭代版
     */
    public static List<Integer> preorderTraversalByIte(TreeNode root) {
        //使用栈的特性，就像递归版本的一样，添加的子节点，注意是子节点不为空才添加
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        //循环遍历
        while (stack.size() > 0) {
            //弹出实例
            TreeNode pop = stack.pop();
            //保存
            if (root != null)
                stack.add(root);
            //注意顺序，一定要是先右后左
            if (pop.right != null)
                stack.add(pop.right);
            if (pop.left != null)
                stack.add(pop.left);
        }
        return result;
    }

    /**
     * 前序遍历_官方版本
     * 核心思想 :
     *      1.首先一直向左向下，过程中需要记录该节点的值。同时入栈，直到没有左孩子的节点，该节点称为A，
     *      2.这个时候可以弹出A了，然后获取A的右孩子B，重复1过程。
     *      ps:弹出A 的原因，因为右孩子已经爱遍历了，A的使命完成了，如果继续存在就会造成重复遍历 B。
     *      3.重复 1,2 过程，使得栈为空
     *
     *     前中后 迭代遍历三者之间异同:
     *      1.都是从左孩子开始，一直遍历到左孩子为空的节点A，并且要弹出该节点A。回去A的右孩子，对右孩子进行1过程的重复。
     *      2.前序遍历 : 要在左下寻找中记录节点值； 中序遍历要在 左孩子处理完成， 右孩子还没有开始遍历的时候记录节点值；
     *        后续遍历 : 需要对右孩子进行判断如果右孩子有空或者已近遍历过了，那么就记录节点值(需要一个额外的变脸保存右孩子引用，判断是否已经遍历了)
     *                  ,否则 节点入占并对右孩子处理。
     */
    private static List<Integer> preorderTraversalByItePro(TreeNode root) {
        //声明栈
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //不为空的情况下，才 入栈
        TreeNode tempNode = root;
        //开始遍历
        while (stack.size() > 0 || tempNode != null) {
            //一直左遍历
            while (tempNode != null) {
                //先添加数据
                result.add(tempNode.val);
                //入栈
                stack.add(tempNode);
                //变成左孩子
                tempNode = tempNode.left;
            }
            //那么没有左孩子的节点出列,遍历该节点的右孩子就好，记得要弹出该节点，应该该节点已经遍历完成了
            tempNode = stack.pop();
            tempNode = tempNode.right;
        }
        //结果返回
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
