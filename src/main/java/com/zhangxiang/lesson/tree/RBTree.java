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
    protected void afterRemove(Node<E> node, Node<E> replacementNode) {
        //被删除的是红色 不用处理
        if (isRed(node)) {
            return;
        }
        //删的黑色 并且 用于取代的节点是红色
        if (isRed(replacementNode)) {
            black(replacementNode);
            return;
        }
        //删除的是根节点
        Node<E> parent = node.parent;
        if (parent == null) {
            return;
        }
        //删除的是黑色叶子结点
        //判断被删除的node是左孩子还是孩子
        boolean left = parent.left == null || node.isLeftChild();
        //拿到兄弟节点
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) {
            if (isRed(sibling)) {//如果兄弟节点是红色，那么将parent右旋
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //旋转后兄弟节点变了，更新兄弟节点
                sibling = parent.right; //此时兄弟节点一定是黑色
            }
            //兄弟节点是黑色
            if (isBlack(sibling.right) && isBlack(sibling.left)) {
                //此时兄弟节点子节点都是黑色 不能向兄弟节点借元素 发生下溢
                red(sibling);
                //记录parent的颜色，如果是黑色 parent也会发生下溢
                boolean colorOfParent = isBlack(parent);
                black(parent);
                if (colorOfParent) {
                    afterRemove(parent, null);
                }
            } else {//兄弟节点至少一个红色子节点 可以向兄弟节点借元素
                if (isBlack(sibling.right)) {
                    //RL
                    rotateRight(sibling);
                    //此时经过旋转兄弟节点可能发生变化
                    sibling = parent.right;
                }
                //先染色在旋转
                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else {//被删除的节点在右边，兄弟在左边
            if (isRed(sibling)) {//如果兄弟节点是红色，那么将parent右旋
                black(sibling);
                red(parent);
                rotateRight(parent);
                //旋转后兄弟节点变了，更新兄弟节点
                sibling = parent.left; //此时兄弟节点一定是黑色
            }
            //兄弟节点是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //此时兄弟节点子节点都是黑色 不能向兄弟节点借元素 发生下溢
                red(sibling);
                //记录parent的颜色，如果是黑色 parent也会发生下溢
                boolean colorOfParent = isBlack(parent);
                black(parent);
                if (colorOfParent) {
                    afterRemove(parent, null);
                }
            } else {//兄弟节点至少一个红色子节点 可以向兄弟节点借元素
                if (isBlack(sibling.left)) {
                    //LR
                    rotateLeft(sibling);
                    //此时经过旋转兄弟节点可能发生变化
                    sibling = parent.left;
                }
                //先染色在旋转
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
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
