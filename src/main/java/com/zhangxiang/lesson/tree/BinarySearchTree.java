package com.zhangxiang.lesson.tree;

import com.zhangxiang.lesson.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月09日 11:36:02
 * @desc: 二叉查找树
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator; //比较元素大小的规则

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = new Node<>(element, null);
            size++;
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
        Node<E> newNode = new Node<>(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }
        return ((Comparable<E>) element1).compareTo(element2);
    }

    public void remove(E element) {
        Node<E> node = node(element);
        if(node == null){
            return;
        }
        if(node.hasTwoChildren()){//度为2的节点
            //找到它的前驱或者后继节点  这里用后继节点
            Node<E> successor = successor(node);
            node.element = successor.element; //用后继节点的值覆盖要删除节点的值，然后删除后继节点
            node = successor;
        }
        //找出替代 被删除节点 的节点
        Node<E> replacementNode = node.left == null ? node.right : node.left;
        if(replacementNode != null){//删除的是度为1的节点
            //首先维护parent值
            replacementNode.parent = node.parent;
            if(node.parent == null){
                root = replacementNode;
            }else if (node == node.parent.left){
                node.parent.left = replacementNode;
            }else {
                node.parent.right = replacementNode;
            }
        }else {//删除的是叶子节点
            if(node.parent == null){//root节点
                root = null;
            }else if(node == node.parent.left){
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
        }
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

    public boolean contains(E Element) {
        return false;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must be not null");
        }
    }

    /**
     * 用于图形打印树的各个节点
     *
     * @return
     */
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    public static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 是否是叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 有两个孩子节点
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    /**
     * 前序遍历: 根节点、左子树、右子树
     */
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        visitor.visit(node);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历：左子树、根节点、右子树
     */
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, visitor);
        visitor.visit(node);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历：左子树、右子树、根节点
     */
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        visitor.visit(node);
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal(Visitor<E> visitor) {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    @FunctionalInterface
    public interface Visitor<E> {
        void visit(Node<E> node);
    }

    /**
     * 求高度
     *
     * @return
     */
    public int height() {
        //递归
        //return heightRecursive(root);
        //非递归
        return height(root);
    }

    /**
     * 递归求高度
     *
     * @param node
     * @return
     */
    private int heightRecursive(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    /**
     * 非递归(利用层序遍历)求高度
     *
     * @param node
     * @return
     */
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int levelSize = 1;//记录每一层节点的个数 默认为1
        int height = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 判断是否是完全二叉树
     *
     * @return
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;//叶子节点的标志位
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                //以后全是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        node = node.left;
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }
        //左子树没有 找父节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /**
     * 后继节点
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        node = node.right;
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        //右子树没有 找父节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        //node.parent == null
        // node == node.parent.left
        return node.parent;
    }
}
