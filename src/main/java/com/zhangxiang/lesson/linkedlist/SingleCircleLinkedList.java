package com.zhangxiang.lesson.linkedlist;

import com.zhangxiang.lesson.common.AbstractList;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 12:42:53
 * @desc: 单向循环链表
 */
public class SingleCircleLinkedList<T> extends AbstractList<T> {
    private Node<T> first;

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        return getNode(index).element;
    }

    /**
     * @param index
     * @param element
     * @return
     */
    @Override
    public T set(int index, T element) {
        Node<T> node = getNode(index);
        T oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public int indexOf(T element) {
        Node<T> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return DEFAULT_ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    /**
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        if (index == 0) {
            Node<T> newFirst = new Node<>(element, first);
            //获取最后一个节点 循环处理
            Node<T> lastNode = size == 0 ? newFirst : getNode(size - 1);
            first = newFirst;
            lastNode.next = first;
        } else {
            Node<T> prev = getNode(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        rangeCheck(index);
        Node<T> node = first;
        if (index == 0) {
            Node<T> lastNode = getNode(size - 1);
            first = node.next;
            if (size != 1) {
                //循环处理
                lastNode.next = first;
            }
        } else {
            Node<T> prev = getNode(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    private Node<T> getNode(int index) {
        rangeCheck(index);
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        Node<T> node = first;
        for (int i = 0; ; i++) {
            b.append(node.element);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
            node = node.next;
        }
    }
}
