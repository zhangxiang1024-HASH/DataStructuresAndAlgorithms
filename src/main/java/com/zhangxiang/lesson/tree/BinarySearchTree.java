package com.zhangxiang.lesson.tree;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月09日 11:36:02
 * @desc: 二叉搜索树
 */
public class BinarySearchTree<E> extends BinaryTree<E> {
    protected Comparator<E> comparator; //比较元素大小的规则

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }
        Node<E> parent = root;
        Node<E> node = root;
        int compare = 0;
        while (node != null) {
            compare = compare(element, node.element);
            parent = node;
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {//相等
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     * 添加之后的操作
     * @param node
     */
    protected void afterAdd(Node<E> node){
    }

    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }
        return ((Comparable<E>) element1).compareTo(element2);
    }

    public void remove(E element) {
        Node<E> node = node(element);
        if (node == null) {
            return;
        }
        size--;
        if (node.hasTwoChildren()) {//度为2的节点
            //找到它的前驱或者后继节点  这里用后继节点
            Node<E> successor = successor(node);
            node.element = successor.element; //用后继节点的值覆盖要删除节点的值，然后删除后继节点
            node = successor;
        }
        //找出替代 被删除节点 的节点
        Node<E> replacementNode = node.left == null ? node.right : node.left;
        if (replacementNode != null) {//删除的是度为1的节点
            //首先维护parent值
            replacementNode.parent = node.parent;
            if (node.parent == null) {
                root = replacementNode;
            } else if (node == node.parent.left) {
                node.parent.left = replacementNode;
            } else {
                node.parent.right = replacementNode;
            }
            afterRemove(node,replacementNode);
        } else {//删除的是叶子节点
            if (node.parent == null) {//root节点
                root = null;
            } else if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node,null);
        }
    }

    protected void afterRemove(Node<E> node,Node<E> replacementNode){

    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must be not null");
        }
    }
}
