package com.zhangxiang.lesson.tree;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月22日 19:21:41
 * @desc: 二叉平衡查找树
 */
public class AVLTree<E> extends BinarySearchTree<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null){
            //平衡
            if(isBalanced(node)){
                //更新高度
            }else {//不平衡
                //恢复平衡
            }
        }
    }

    /**
     *
     * @param grand 高度最低的 失衡节点
     */
    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 判断入参节点是否平衡
     *
     * @param node
     * @return
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E>  extends Node<E>{
        int height;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 当前节点的平衡因子
         *
         * @return
         */
        public int balanceFactor() {
            //左子树高度
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            //右子树高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新当前节点的高度
         */
        public void updateHeight() {
            //左子树高度
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            //右子树高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            //左子树高度
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            //右子树高度
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight - rightHeight > 0) {
                return left;
            } else if (leftHeight - rightHeight < 0) {
                return right;
            } else {
                return this.isLeftChild() ? left : right;
            }
        }
    }
}
