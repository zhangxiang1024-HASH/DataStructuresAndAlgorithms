package com.zhangxiang.lesson.tree;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月01日 20:25:49
 * @desc: 平衡二叉搜索树
 */
public class BBSTree<E> extends BinarySearchTree<E> {
    public BBSTree() {
        this(null);
    }

    public BBSTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 左旋转
     * @param grand
     */
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     *
     * @param grand
     */
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //更新 parent的parent
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {//根节点
            root = parent;
        }
        //更新child的 parent
        if (child != null) {
            child.parent = grand;
        }
        //更新grand的parent
        grand.parent = parent;
    }
}
