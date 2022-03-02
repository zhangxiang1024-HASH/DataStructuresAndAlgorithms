package com.zhangxiang.lesson.tree;

import java.util.Comparator;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月01日 18:40:50
 * @desc:  红黑树(自平衡二叉搜索树)
 */
public class RBTree<E> extends BBSTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //如果添加的是root节点 染成黑色
        if(parent == null){
            black(node);
            return;
        }
        //父节点是黑色不用处理
        if(isBlack(parent)){
            return;
        }

        //uncle 节点
        Node<E> uncle = parent.sibling();
        //祖父节点
        Node<E> grand = parent.parent;
        //叔父节点是红色
        if(isRed(uncle)){
            //发生上溢 将parent、uncle染成黑色
            black(parent);
            black(uncle);
            //将祖父节点染成红色 当做新添加的节点 递归调用此方法
            afterAdd(red(grand));
            return;
        }
        //叔父节点是黑色
        if(parent.isLeftChild()){
            if(node.isLeftChild()){//LL
                black(parent);
                red(grand);
                rotateRight(grand);
            }else {//LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {
            if(node.isRightChild()){//RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }else {//RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node,Node<E> replacementNode) {
        //被删除的是红色 不用处理
        if(isRed(node)){
            return;
        }
        //删的黑色 并且 用于取代的节点是红色
        if(isRed(replacementNode)){
            black(replacementNode);
            return;
        }
        //删除的是根节点
        if(node.parent == null){
            return;
        }
        //删除的是黑色叶子结点




    }

    /**
     * 染色
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>) node).color = color;
        return node;
    }

    /**
     * 判断颜色
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isRed(Node<E> node) {
        return RED == colorOf(node);
    }

    private boolean isBlack(Node<E> node) {
        return BLACK == colorOf(node);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E>{
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }


    }

    @Override
    public Object string(Object node) {
        String str = "";
        if (((RBNode<E>)node).color == RED) {
            str = "R_";
        }
        return str + ((RBNode<E>)node).element.toString();
    }
}
