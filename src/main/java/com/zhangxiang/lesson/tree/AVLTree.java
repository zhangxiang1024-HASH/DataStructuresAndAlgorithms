package com.zhangxiang.lesson.tree;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月22日 19:21:41
 * @desc: AVL树(一种自平衡的二叉搜索树)
 */
public class AVLTree<E> extends BBSTree<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 调整最近的失衡的祖先节点
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            //平衡
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {//不平衡
                //恢复平衡
                reBalance(node);
                break;
            }
        }
    }

    /**
     * 从下一直向上调整
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            //平衡
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {//不平衡
                //恢复平衡
                reBalance(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        //更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     *
     * @param grand 高度最低的 失衡节点
     */
    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { //LL 右旋转
                rotateRight(grand);
            } else {//LR 先对p进行RR左旋转，再对g进行LL右旋转
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isRightChild()) { //RR 左旋转
                rotateLeft(grand);
            } else {//RL 先对p进行LL右旋转，再对g进行RR左旋转
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
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
        int height = 1;
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
