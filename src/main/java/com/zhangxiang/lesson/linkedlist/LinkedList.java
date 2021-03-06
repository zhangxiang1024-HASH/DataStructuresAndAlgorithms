package com.zhangxiang.lesson.linkedlist;

import com.zhangxiang.lesson.common.AbstractList;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 12:42:53
 * @desc: 双链表
 */
public class LinkedList<T> extends AbstractList<T> {
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        public Node(T element, Node<T> next, Node<T> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
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
        last = null;
    }

    /**
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        if (index == size) {//在最后面添加
            Node<T> node = new Node<>(element, null, last);
            last = node;
            if (size == 0) { //链表第一个元素
                first = node;
            } else {
                node.prev.next = node;
            }
        } else {
            Node<T> next = getNode(index);
            Node<T> prev = next.prev;
            Node<T> node = new Node<>(element, next, prev);
            next.prev = node;
            if (prev == null) {
                first = node;
            } else {
                prev.next = node;
            }
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
        Node<T> node = getNode(index);
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
        size--;
        return node.element;
    }

    private Node<T> getNode(int index) {
        rangeCheck(index);
        Node<T> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
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
