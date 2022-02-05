package com.zhangxiang.lesson.linkedlist;

import com.zhangxiang.lesson.common.AbstractList;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 12:42:53
 * @desc: 单链表  增加虚拟头结点 以统一所有节点处理逻辑
 */
public class SingleLinkedList2<T> extends AbstractList<T> {
    private Node<T> first;

    public SingleLinkedList2() {
        first = new Node<>(null, null);
    }

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public T get(int index) {
        return getNode(index).element;
    }

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

    @Override
    public void add(int index, T element) {
        Node<T> prev = index == 0 ? first : getNode(index - 1);
        prev.next = new Node<>(element, prev.next);
        size++;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        Node<T> prev = index == 0 ? first : getNode(index - 1);
        Node<T> node = prev.next;
        prev.next = node.next;
        size--;
        return node.element;
    }

    private Node<T> getNode(int index) {
        rangeCheck(index);
        Node<T> node = first.next;
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
        Node<T> node = first.next;
        for (int i = 0; ; i++) {
            b.append(node.element);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
            node = node.next;
        }
    }
}
